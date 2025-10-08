import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1149번
    // RGB 거리
    // 이것도 유명한 DP문제이고,
    // 상태를 저장해야하므로 이차원 배열을 저장해야 할 것 같다.
    // 0은 R, 1은 G, 2는 B 이런 식으로?

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dp = new int[N][3];
        StringTokenizer st = new StringTokenizer(br.readLine());

        dp[0][0] = Integer.parseInt(st.nextToken());
        dp[0][1] = Integer.parseInt(st.nextToken());
        dp[0][2] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + Integer.parseInt(st.nextToken());
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + Integer.parseInt(st.nextToken());
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2]))));

        br.close();
        bw.flush();
        bw.close();
    }

}
