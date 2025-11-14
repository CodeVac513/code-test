import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 2098
    // DP - 비트마스킹
    // N개의 도시에 대한 상태를 추적해야 함.
    // 비트마스킹을 통해서 쉽게 추적할 수 있을 듯함.
    // 최단 경로를 구해야 하는데 어떤 도시에서 출발하든 사이클이 생기기 때문에 시작 도시를 옮겨야 할 필요는 없음.
    //

    static int[][] cost;
    static int[][] dp;
    static int N;
    static final int INF = 1000000 * 16 + 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][1 << N];
        cost = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(String.valueOf(solve(0, 1)));

        bw.flush();
        bw.close();
        br.close();
    }

    static int solve(int startCity, int currentVisitedCity) {
        if (currentVisitedCity == (1 << N) - 1) {
            if (cost[startCity][0] == 0) {
                return INF;  // 갈 수 없음
            }
            return cost[startCity][0];
        }
        if (dp[startCity][currentVisitedCity] != 0) {
            return dp[startCity][currentVisitedCity];
        }
        int minValue = INF;
        for (int i = 0; i < N; i++) {
            if ((currentVisitedCity & (1 << i)) != 0) continue;

            if (cost[startCity][i] == 0) continue;

            minValue = Math.min(minValue, solve(i, (currentVisitedCity | (1 << i))) + cost[startCity][i]);
        }
        dp[startCity][currentVisitedCity] = minValue;
        return dp[startCity][currentVisitedCity];
    }
}