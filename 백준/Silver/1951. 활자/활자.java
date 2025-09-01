import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1951번
    // 구현
    // 자연수를 표현하기 위해서는 각각 숫자를 한 자리씩 떼어 생각한다.
    // 10 -> 1, 0
    // 100 -> 1, 0, 0
    // N이 20억 이하라서 Integer로 모든 숫자를 표현할 수 없다. Long을 사용하자.
    // 자리수에 따라서 더해주는 개수를 구한다.
    // N이 한 자리 -> 1부터 N까지
    // N이 두 자리 -> 1부터 9까지(1 * (i + 1)), 10부터 N까지 (2* (N-10+1))
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long N = Long.parseLong(br.readLine());

        Long result = 0L;
        Integer divideNum = 1234567;
        Integer nDigit = String.valueOf(N).length();
        for (int i = 0; i < nDigit; i++) {
            Long startNum = (long) Math.pow(10, (double) i);

            if (i == nDigit - 1) {
                Long count = N - startNum + 1;
                result += (count * (i + 1)) % divideNum;
            } else {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < i + 1; j++) {
                    sb.append(9);
                }
                Long endNum = Long.parseLong(sb.toString());
                Long count = endNum - startNum + 1;
                result += (count * (i + 1)) % divideNum;
            }
            result %= divideNum;
        }

        bw.write(String.valueOf(result % divideNum));

        br.close();
        bw.flush();
        bw.close();
    }
}
