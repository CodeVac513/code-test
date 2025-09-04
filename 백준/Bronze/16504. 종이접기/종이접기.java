import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 16504번
    // 구현, 수학
    // 가로 세로는 2^N 이므로 접으면 항상 짝수이다.
    // 그래서 받은 배열의 모든 숫자를 다 더하면 결국 남는 하나의 값이 된다.
    // 각 칸에는 최댓값이 10^5이고 가로 세로로 2^10개가 최대로 있을 수 있다.
    // 대충 가로 세로 값 N = 1000이라 보면 10^3 * 10 ^ 3 * 10^5 = 10^11이 최대값이 될 수 있으므로 Long으로 연산해야 한다.

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long sum = 0L;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                sum += Integer.parseInt(st.nextToken());
            }
        }

        bw.write(String.valueOf(sum));
        br.close();
        bw.flush();
        bw.close();
    }
}
