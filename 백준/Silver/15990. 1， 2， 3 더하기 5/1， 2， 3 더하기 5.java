import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 15990번
    // DP? 누적합? 뭔가 누적되는 알고리즘인 것 같은데
    // 1 -> 1
    // 2 -> 2
    // 3 -> 3, 1+2, 2+1
    // 4 -> 1+3, 3+1, 1+2+1
    // 5 -> 1+3+1
    // 2+3, 2+1+2
    // 3+2
    // 4+1 => 1+3+1이므로 1과 겹침, 총 4개
    // 6 -> 1+ 5의 가짓수 중 4개, 1+2+3, 1+2+1+2, 1+3+2
    // 2+ 4의 가짓수 중 2개, 2+1+3, 2+3+1, 2+1+2+1
    // 3+3, 3+2+1, 3+1+2
    // 5의 가짓수 중 3개 + 1, 2+3+1, 2+1+2+1, 3+2+1 => 모두 겹침, 총 8개
    // 7 -> 1+6 : 1+2+1+3, 1+2+3+1, 1+2+1+2+1, 1+3+2+1, 1+3+1+2
    // 2+5 : 2+1+3+1, 2+3+2
    // 3+4 : 3+1+3, 3+1+2+1 => 총 9개
    // 1,2,3 인덱스 3개를 두고 각각 무슨 숫자로 시작하는지 구해놓고 DP로 풀면 될 듯?(일종의 점화식)
    // 오답 노트)
    // dpArray에 값을 저장할 때, MOD 변수로 모듈러 연산을 미리 진행해줘야 오버플로우가 발생하지 않는다.

    static long[][] dpArray = new long[100001][4];
    static long MOD = 1000000009;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        makePrefixSumArray();
        for (int testCase = 0; testCase < tc; testCase++) {
            int n = Integer.parseInt(br.readLine());
            long ans = (dpArray[n][1] + dpArray[n][2] + dpArray[n][3]) % MOD;
            bw.write(ans + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void makePrefixSumArray() {
        dpArray[1][1] = 1;
        dpArray[2][2] = 1;
        dpArray[3][1] = 1;
        dpArray[3][2] = 1;
        dpArray[3][3] = 1;
        for (int i = 4; i <= 100000; i++) {
            dpArray[i][1] = (dpArray[i - 1][2] + dpArray[i - 1][3]) % MOD;
            dpArray[i][2] = (dpArray[i - 2][1] + dpArray[i - 2][3]) % MOD;
            dpArray[i][3] = (dpArray[i - 3][1] + dpArray[i - 3][2]) % MOD;
        }
    }
}

