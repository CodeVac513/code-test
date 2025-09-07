import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 16673번
    // 구현
    // 문제에 주어진대로 K*n + P*n^2을 활용해서 값을 구한다.
    // 데이터 타입에 대해 생각했을 때, 목표 C년이 100년차이면 대충 10^7 정도의 값을 가질 수도 있다. 아슬아슬할 지도?

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        long ans = 0L;
        for (int i = 1; i <= C; i++) {
            ans += (long) K * i + (long) P * i * i;
        }

        bw.write(String.valueOf(ans));

        br.close();
        bw.flush();
        bw.close();
    }
}



