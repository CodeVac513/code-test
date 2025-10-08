import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 2579번
    // dp
    // 중요한 것은 divide and conquer, 작은 문제로 분리해서 생각할 줄 알아야 함.
    // 이 문제에서 중요한 것은 `바로 전 칸에서 오는 경우`와 `2칸 전에서 오는 경우` 중 더 큰 것을 고르는 것.
    // 그리고 연속된 3개의 계단을 밟을 수 없다는 조건을 생각해야 한다.
    // 그러면 이 DP는 이전 상태를 기억하는 DP 여야 한다.
    // 이차원 배열로 dp 배열을 선언하고
    // 0에서는 바로 이전에서 온 값을 저장하고
    // 1에서는 2칸 전에서 온 값을 저장해야 한다.
    // 그래서 [1]에 저장될 때에는 Math.max로 이전 단계에서 더 큰 값을 가져올 수 있도록 해야 한다.
    // [0]에 저장될 때는 i - 1이 2칸을 건너뛰어 도착한 상태에서만 사용할 수 있음. 안 그러면 3칸 연속 규칙 위배.

    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] stairs = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        // dp 배열 초기화
        dp = new int[N + 1][2];
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[1][0] = stairs[1];
        dp[1][1] = stairs[1];

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][1] + stairs[i];
            dp[i][1] = Math.max(dp[i - 2][0], dp[i - 2][1]) + stairs[i];
        }

        bw.write(String.valueOf(Math.max(dp[N][0], dp[N][1])));

        br.close();
        bw.flush();
        bw.close();
    }
}
