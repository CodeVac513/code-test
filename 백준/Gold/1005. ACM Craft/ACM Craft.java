

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1005번
    // 위상 정렬
    // 위상 정렬의 탬플릿은
    // 1. 인접 리스트인 graph
    // 2. 자기 자신을 가리키는 엣지의 개수 배열인 indegree
    // 3. 순서를 지키기 위한 queue
    // 여기에 시간 누적 때문에 DP 개념도 활용해야 함.

    static int N;
    static int K;
    static ArrayList<Integer>[] graph;
    static int[] indegree;
    static int[] buildTime;
    static int[] dp;
    static int targetBuilding;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            graph = new ArrayList[N + 1];
            indegree = new int[N + 1];
            buildTime = new int[N + 1];
            dp = new int[N + 1];
            indegree[0] = -1;

            for (int i = 1; i < N + 1; i++) {
                graph[i] = new ArrayList<>();
                indegree[i] = 0;
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N + 1; i++) {
                buildTime[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                graph[start].add(end);
                indegree[end]++;
            }

            targetBuilding = Integer.parseInt(br.readLine());
            topologicalSort();
            bw.write(dp[targetBuilding] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i <= N; i++) {
            dp[i] = buildTime[i];
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : graph[current]) {
                dp[next] = Math.max(dp[next], dp[current] + buildTime[next]);

                indegree[next]--;
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}
