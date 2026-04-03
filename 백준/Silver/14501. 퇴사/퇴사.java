import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 14501번
    // 얻을 수 있는 최대 수익
    // -> 그리디하게 풀 수 없음.
    // 길이가 짧다고 저수익이고 고수익을 고른다고 해서 저수익 여러 개보다 낫다는 보장 없음.
    // 즉, 정렬 불가
    // 모든 경우의 수를 다 따져봐야 함.
    // 가볼 수 있는 곳을 모두 가면서 MAX 값을 찾으면 될 것 같은데?
    static int N;
    static int[][] counsel;
    static int[] pay;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        counsel = new int[N + 1][2];
        pay = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            counsel[i][0] = Integer.parseInt(st.nextToken());
            counsel[i][1] = Integer.parseInt(st.nextToken());
        }

        solve(1, 0);

        bw.write(pay[N] + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solve(int start, int paySum) {
        if (start == N + 1) {
            pay[N] = Math.max(paySum, pay[N]);
            return;
        }

        if (start + counsel[start][0] <= N + 1) {
            solve(start + counsel[start][0], paySum + counsel[start][1]);
        }

        solve(start + 1, paySum);
    }
}


