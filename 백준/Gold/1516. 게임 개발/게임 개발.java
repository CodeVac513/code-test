

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1516번
    // 위상 정렬
    // ACM craft와 비슷한 문제, DP로 건물 짓는 시간을 누적시키면서, 모든 경우의 수를 수집.

    static int N;
    static ArrayList<Integer>[] graph;
    static int[] indegree;
    static int[] dp;
    static int[] buildTime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        dp = new int[N + 1];
        indegree = new int[N + 1];
        buildTime = new int[N + 1];
        indegree[0] = -1;
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            indegree[i] = 0;
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            buildTime[i] = Integer.parseInt(st.nextToken());
            while (true) {
                int start = Integer.parseInt(st.nextToken());

                if (start == -1)
                    break;

                graph[start].add(i);
                indegree[i]++;
            }

        }

        topologicalSort();
        for (int i = 1; i <= N; i++) {
            bw.write(dp[i] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                dp[i] = buildTime[i];
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
