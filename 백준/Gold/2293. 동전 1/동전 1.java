import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 2293번
    // dp 문제
    // n개의 동전을 적당히 사용해서 K원을 만들어야 함.

    static int n;
    static int k;
    static int[] coins;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        coins = new int[n];
        dp = new int[k + 1];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        dp[0] = 1;
        // 각 동전에 대해
        for (int i = 0; i < n; i++) {
            // 해당 동전으로 만들 수 있는 모든 금액 갱신
            for (int j = coins[i]; j <= k; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        bw.write(String.valueOf(dp[k]));
        bw.flush();
        bw.close();
        br.close();
    }

}
