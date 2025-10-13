import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 14565번
    // 확장 유클리드 호제법
    // (a + b) % n = 0 -> b는 a의 덧셈역
    // (a * c) % n = 1 -> c는 a의 곱셈역
    // -a ≡ -a + n (mod n)
    // -a ≡ n - a (mod n)
    // 음수 모듈러 연산 -> a % n, a < 0 일 때 a가 양수라고 생각한 뒤 모듈러 연산을 진행하고,
    // 음수로 부호변환 한 뒤 + n을 한다.
    // -(A % n) + n (A = -a, a<0)

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        long A = Long.parseLong(st.nextToken());

        bw.write(String.valueOf(-(A % N) + N) + " ");

        bw.write(String.valueOf(extendedGCD(N, A)));

        br.close();
        bw.flush();
        bw.close();
    }

    public static long extendedGCD(long a, long b) {
        long r0 = a, r1 = b;
        long x0 = 1, x1 = 0;
        long y0 = 0, y1 = 1;

        while (r1 != 0) {
            long q = r0 / r1;
            long r2 = r0 - q * r1;

            long x2 = x0 - q * x1;
            long y2 = y0 - q * y1;

            // System.out.printf("%d | %d | %d\n", x2, y2, r2);

            r0 = r1;
            r1 = r2;
            x0 = x1;
            x1 = x2;
            y0 = y1;
            y1 = y2;
        }

        if (r0 != 1) {
            return -1;
        }
        return (y0 % a + a) % a;
    }

}



// x | y | 12345*x + 123*y (유클리드 호제법 사용시 r-나머지-에 해당하는 부분)
// 1 | 0 | 12345
// 0 | 1 | 123
// 1 |-100 | 45
// -2 | 201 | 33
// 3 |-301 | 12
// -8 | 803 | 9
// 11 |-1104| 3
