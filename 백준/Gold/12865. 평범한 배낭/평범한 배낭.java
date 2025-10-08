import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 12865번
    // DP
    // 문제에서 주어지는 한계 k가 dp의 index가 될 것 같은데?
    // 그리고 dp[W] = Math.max(dp[W], V)와 같은 식으로 dp[W]를 업데이트 시키고
    // dp[K] = Math.max(dp[K], dp[K-W])가 될 듯?

    // 오답 노트)
    // 모든 무게가 고려되어야 하는 것을 잊으면 안됨.
    // dp[W]가 새롭게 입력되면, dp[W]부터 dp[K]까지(W < K일 때)모든 무게를 업데이트 해야 함.
    // 다시 정리하면 무게 W, 가치 V인 물건이 있으면, 이 물건이 들어갈 수 있는 모든 무게를 업데이트 해야 함.
    // 그리고 뒤에서부터 순회해야 함. 앞에서부터 순회하면 다음과 같은 상황이 벌어질 수 있음.
    // W = 2이고, K = 10이라 가정
    // dp[2] = Math.max(0, 2)로 업데이트,
    // dp[4] = Math.max(0, dp[2] + 2)가 되는데, 같은 물건이 두 번 중첩됨.

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dp = new int[K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            if (W > K)
                continue;

            for (int w = K; w >= W; w--) {
                dp[w] = Math.max(dp[w], dp[w - W] + V);
            }
        }

        bw.write(String.valueOf(dp[K]));

        br.close();
        bw.flush();
        bw.close();
    }

}
