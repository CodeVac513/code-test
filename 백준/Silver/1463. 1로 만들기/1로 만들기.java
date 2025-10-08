import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1463번
    // DP
    // 중요한 것은 점화식
    public static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        dp[1] = 0;
        // dp[2] = 1;
        // dp[3] = 1;
        for (int i = 2; i <= N; i++) {
            int k = Integer.MAX_VALUE;
            if (i % 3 == 0) {
                k = dp[i / 3] + 1;
            }
            if (i % 2 == 0) {
                k = Math.min(k, dp[i / 2] + 1);
            }
            dp[i] = Math.min(k, dp[i - 1] + 1);
        }

        bw.write(String.valueOf(dp[N]));
        br.close();
        bw.flush();
        bw.close();
    }
}
