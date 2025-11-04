

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1766번
    // 위상 정렬
    // 오답 노트)
    // 문제는 난이도 순서라는 조건이 있다. -> Queue가 항상 오름차순으로 정렬되어 있어야 한다는 의미
    // 따라서 priority Queue를 사용해서 항상 정렬된 값을 가져올 수 있도록 해야 한다.

    static int N;
    static int M;
    static ArrayList<Integer>[] graph;
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        indegree = new int[N + 1];
        indegree[0] = -1;
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            indegree[i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start].add(end);
            indegree[end]++;
        }

        bw.write(topologicalSort());

        bw.flush();
        bw.close();
        br.close();
    }

    static String topologicalSort() {
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 0; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int current = q.poll();
            sb.append(current).append(" ");

            for (int next : graph[current]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        return sb.toString();
    }
}
