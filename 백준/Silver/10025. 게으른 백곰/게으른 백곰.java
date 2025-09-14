import java.math.BigInteger;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 10025번
    // 슬라이딩 윈도우
    // N개의 얼음 양동이가 각각 1칸을 차지한다.
    // 가로로 1000000개까지 칸이 있을 수 있다.
    // 양동이 안에는 1이상 10000이하의 얼음이 들어있다.
    // 좌우로 K만큼 떨어진 양동이까지 닿는데, 가장 얼음 총량이 많은 범위를 구해야 한다.
    // 오답 노트)
    // 같은 위치에 얼음 개수를 더할 수도 있음.

    static int maximumX = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coordinate = new int[maximumX];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            coordinate[x] += g;
        }

        int sum = 0;
        int windowSize = 2 * K + 1;
        for (int i = 0; i < windowSize && i < maximumX; i++) {
            sum += coordinate[i];

        }
        int maxSum = sum;

        for (int i = windowSize; i < maximumX; i++) {
            sum = sum + coordinate[i] - coordinate[i - windowSize];
            maxSum = Math.max(maxSum, sum);
        }
        bw.write(String.valueOf(maxSum));

        br.close();
        bw.flush();
        bw.close();
    }

}
