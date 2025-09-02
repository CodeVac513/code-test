import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 7523번
    // 수학
    // (n)(n+1)/2를 활용한다.
    // 단, 범위가 integer를 넘어갈 수 있기에 long으로 답안을 출력한다.

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for (int i = 1; i <= testCase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());
            long m = Long.parseLong(st.nextToken());
            long ans = calculate(n, m);
            bw.write("Scenario #" + i + ":\n");
            bw.write(String.valueOf(ans) + "\n");
            bw.write("\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static long calculate(long n, long m) {
        long sum = m * (m + 1) / 2;
        long subtractionSum = (n - 1) * n / 2;
        return sum - subtractionSum;
    }
}
