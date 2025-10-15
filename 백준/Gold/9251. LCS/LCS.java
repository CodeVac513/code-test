import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 9251번
    // DP
    // A와 B의 부분 문자열을 분해해서 모든 문자열에 대해 최댓값을 저장한다.

    static int[][] dp;


    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();
        dp = new int[A.length() + 1][B.length() + 1];
        getLCS(A, B);
        bw.write(dp[A.length()][B.length()] + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    public static void getLCS(String A, String B) {
        for (int i = 0; i <= A.length(); i++) {
            for (int j = 0; j <= B.length(); j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
    }
}
