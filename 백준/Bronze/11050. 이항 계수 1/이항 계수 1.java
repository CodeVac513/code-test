import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 11050번
    // 조합
    // 이항 계수, 즉 조합을 계산하는 문제
    // 구체적인 대상을 구하는 게 아니라 수학적으로 연산만 진행함. n! / (r! * (n-r)!)만 기억하면 됨

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int ans = 1;
        for (int i = 1; i <= N; i++) {
            ans *= i;
        }

        int divide = 1;
        for (int i = 1; i <= M; i++) {
            divide *= i;
        }

        for (int i = 1; i <= N - M; i++) {
            divide *= i;
        }

        bw.write(String.valueOf(ans / divide));

        br.close();
        bw.flush();
        bw.close();
    }
}
