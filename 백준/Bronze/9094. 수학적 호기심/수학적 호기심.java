import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 9094번
    // 수학, 구현문제
    // 0<a<b<n이고 n과 m은 100이하의 값이므로 모든 a와 b를 브루트포스로 연산해도 최대 약 1만번의 연산을 진행한다.
    // 1초에 1억번의 연산을 기대하므로, 모든 경우의 수를 확인해도 시간초과가 발생하지 않을 것으로 예상.
    // 실수 나눗셈을 사용하면 부동소수점으로 인한 정밀도 손실이 발생할 수 있음.
    // 나머지 연산을 통해서 확인해야 함.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < t; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int result = 0;
            for (int a = 1; a < n - 1; a++) {
                for (int b = a + 1; b < n; b++) {
                    if ((a * a + b * b + m) % (a * b) == 0) {
                        result++;
                    }
                }
            }
            bw.write(String.valueOf(result) + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}