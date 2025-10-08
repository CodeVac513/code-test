import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1932번
    // dp
    // 자칫하면 그리디로 착각하기 쉬움.
    // 가장 큰 값을 골라 내려온다는 것이 최적의 해를 보장하지 못함.
    // 따라서 모든 경우를 탐색해야하고, DP(메모이제이션)를 활용하는 것이 좋아보임.
    // 단, DP 배열의 형태는 어떻게 되어야 할 지 잘 모르겠음.
    // -> arrayList로 두고 값을 받아서 삽입할 때 이전 단계 중 큰 값을 골라서 삽입하도록 하자.

    static ArrayList<Integer>[] dp;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        dp = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = new ArrayList();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            if (i == 1) {
                dp[i].add(Integer.parseInt(st.nextToken()));
                continue;
            }

            int iter = st.countTokens();
            for (int k = 0; k < iter; k++) {
                if (k == 0) {
                    dp[i].add(dp[i - 1].get(0) + Integer.parseInt(st.nextToken()));
                } else if (k == iter - 1) {
                    dp[i].add(
                            dp[i - 1].get(dp[i - 1].size() - 1) + Integer.parseInt(st.nextToken()));
                } else {
                    dp[i].add(Math.max(dp[i - 1].get(k - 1), dp[i - 1].get(k))
                            + Integer.parseInt(st.nextToken()));
                }
            }
        }

        Collections.sort(dp[N]);
        bw.write(String.valueOf(dp[N].get(dp[N].size() - 1)));

        br.close();
        bw.flush();
        bw.close();
    }
}
