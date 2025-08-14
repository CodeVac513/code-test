import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    // 5043 문제
    // 구현 문제? 수학 문제?
    // b비트로 N개의 서로 다른 파일을 구분할 수 있는지 확인하는 문제
    // 2^b비트로 만들 수 있는 모든 비트의 조합의 경우의 수가 N보다 크면 구분할 수 있다.
    // https://www.acmicpc.net/board/view/49176
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Long N = Long.parseLong(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        Long sum = 0L;
        for (int i = 0; i <= b; i++) {
            sum += (long) Math.pow(2, i);
        }

        if (N > sum) {
            bw.write("no");
        } else {
            bw.write("yes");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
