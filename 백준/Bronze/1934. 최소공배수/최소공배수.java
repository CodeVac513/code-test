import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1934번
    // 최소공배수, 유클리드 호제법
    // 최소공배수 = A * B / 최대공약수;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int gcd = getGcd(Math.max(A, B), Math.min(A, B));
            long lcm = A * B / gcd;

            bw.write(String.valueOf(lcm) + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static int getGcd(int a, int b) {
        if (b == 0)
            return a;
        return getGcd(b, a % b);
    }

}
