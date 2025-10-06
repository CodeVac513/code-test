import java.util.*;
import java.lang.*;
import java.math.BigInteger;
import java.io.*;

public class Main {
    // 1914번
    // 재귀
    // 재귀로 유명한 하노이탑이다.
    // N개를 A→C로 (B 보조)
    //  N-1개를 A→B로 (C 보조)
    //      N-2개를 A→C로 (B 보조)
    //      N-2개를 C→B로 (A 보조)
    //  N-1개를 B→C로 (A 보조)
    //      N-2개를 B→A로 (C 보조)
    //      N-2개를 A→C로 (B 보조)

    // 또 하나의 함정은 N이 20이면 BigInteger를 사용해야 한다.
    // 이동 횟수는 수학적 귀납법, 점화식으로 2^N - 1임을 증명할 수 있다.

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N > 20) {
            BigInteger count = BigInteger.TWO.pow(N).subtract(BigInteger.ONE);
            bw.write(count.toString(10));
        } else {
            int count = (int) Math.pow(2, N) - 1;
            System.out.println(String.valueOf(count));
            hanoi(N, 1, 3, 2);
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void hanoi(int n, int from, int to, int extra) {
        // from을 to로 옮긴다. extra는 보조 기둥이니 필요하면 사용.
        if (n == 1) {
            System.out.println(from + " " + to);
            return;
        }

        hanoi(n - 1, from, extra, to);
        System.out.println(from + " " + to);
        hanoi(n - 1, extra, to, from);
    }
}
