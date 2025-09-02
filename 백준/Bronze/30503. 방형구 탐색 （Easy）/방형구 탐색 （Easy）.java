import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 30503번
    // 시뮬레이션, 구현
    // 문제에 주어진 규칙대로 구현한다.

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int Q = Integer.parseInt(br.readLine());
        int[] flowers = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            flowers[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int queryType = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            if (queryType == 1) {
                // l부터 r까지 꽃의 종류가 k인 꽃의 개수를 출력
                int k = Integer.parseInt(st.nextToken());
                int count = 0;
                for (int j = l; j <= r; j++) {
                    if (flowers[j] == k)
                        count++;
                }
                bw.write(String.valueOf(count) + "\n");
            } else {
                // l부터 r까지 꽃을 밟아 없앤다.
                for (int j = l; j <= r; j++) {
                    flowers[j] = 0;
                }
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
