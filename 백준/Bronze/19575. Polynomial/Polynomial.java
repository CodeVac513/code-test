import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 19575번
    // 구현, 수학
    // a * x + b의 형태가 반복됨을 확인할 수 있다.

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        Long[] A = new Long[N + 1];
        Long[] degree = new Long[N + 1];

        for (int i = 0; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Long.parseLong(st.nextToken());
            degree[i] = Long.parseLong(st.nextToken());
        }
        Long modNum = 1000000007L;
        Long ans = (A[0] * x + A[1]) % modNum;
        for (int i = 2; i < N + 1; i++) {
            ans = (x * ans + A[i]) % modNum;
        }

        bw.write(String.valueOf(ans));

        br.close();
        bw.flush();
        bw.close();
    }
}
