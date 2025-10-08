import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1003번
    // dp
    // 그냥 피보나치 수열 문제임.
    // 하나 생각할 거리는 재귀적으로 호출(탑 다운)하는 경우, 콜스택이 버텨줄지?

    // 오답 노트)
    // dp문제라 적어놓고 dp를 사용안함 ㅋㅋ;
    // 이거 dp[n][0] = dp[n-1][0] + dp[n-2][0];로 놓고 풀어야 함.
    // 0은 0카운트, 1은 1카운트
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int iter = 0; iter < N; iter++) {
            int n = Integer.parseInt(br.readLine());
            dp = new int[n + 1][2];
            dp[0][0] = 1;
            dp[0][1] = 0;
            if (n != 0) {
                dp[1][0] = 0;
                dp[1][1] = 1;
            }

            for (int i = 2; i <= n; i++) {
                dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
                dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
            }

            bw.write(dp[n][0] + " " + dp[n][1]);
            bw.newLine();
        }
        br.close();
        bw.flush();
        bw.close();
    }

}
