

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 2252번
    // 위상 정렬
    // 위상 정렬은 `사이클이 없는 방향 그래프에서 노드 순서를 찾는` 알고리즘이다.
    // 먼저 아래 단어를 익히자.
    // 진입 차수 - 자기 자신을 가리키는 엣지의 개수 (indegree)
    // 1. 그래프를 인접 리스트로 표현한다.
    // 그 과정에서 진입 차수 배열 D를 업데이트한다.
    // 배열 D는 처음에는 0으로 초기화하고, 연결 관계에서 도착하는 노드가 있을 때마다 그 노드의 index의 배열 D에 1을 더한다.
    // 2. 진입 차수 배열에서 진입 차수가 0인 노드를 선택하고,
    // 선택된 노드를 정렬 배열에 저장한다.
    // 이후 인접 리스트에서 선택된 노드가 가리키는 노드들의 진입 차수를 1씩 뺀다.
    // 이 과정을 모든 노드의 진입 차수가 0이 될 때까지 반복한다.

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
        for (int i = 1; i < N + 1; i++) {
            indegree[i] = 0;
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A].add(B);
            indegree[B]++;
        }

        bw.write(topologicalSort());

        bw.flush();
        bw.close();
        br.close();
    }

    static String topologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

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
