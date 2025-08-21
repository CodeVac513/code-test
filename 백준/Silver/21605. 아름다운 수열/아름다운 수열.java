import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 21605번
    // 수학, 구현
    // 문제에서 주어진 b(A) 연산을 그대로 구현한다.
    // 수열 A는 길이가 2N이고 N개의 -1과 N개의 1로 이루어져 있다.
    // 그래서 직접 계산을 해보면 B0는 0, B1 = 1 or -1, B2 = 2 or 0 or -2, B3 = 3 or 1 or -1 or -3
    // 규칙을 찾아보면 Bn = +- n을 최대 혹은 최소값으로 가진다.

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            if (i == N) {
                bw.write("-1 1");
            } else {
                bw.write("1 -1 ");
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }

}