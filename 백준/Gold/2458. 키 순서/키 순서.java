import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 2458번
    // 플로이드 워셜
    // N이 500이므로, 500^3 = 250000*500 = 125000000 => 1초가 넘어갈 수도 있겠는데?
    // 두 학생 키를 비교할 때, a는 b보다 작다. => 방향성이 있는 그래프
    // 다른 학생들을 대상으로 최단 경로를 알 수 있다면 자신의 키가 몇 번째인지 알 수 있고,
    // INF로 초기화된 부분이 그대로 남아있다면 알 수 없다.


    static int[][] distance;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        distance = new int[N][N];
        final int INF = 500 * 500 * (500 - 1) / 2 + 1;
        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], INF);
            distance[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            distance[start][end] = 1;
        }

        dp();

        int answer = 0;
        for (int i = 0; i < N; i++) {
            boolean isKnown = true;
            for (int j = 0; j < N; j++) {
                if (i == j)
                    continue;

                if (distance[i][j] >= INF && distance[j][i] >= INF) {
                    isKnown = false;
                    break;
                }
            }
            if (isKnown)
                answer++;
        }

        bw.write(String.valueOf(answer));

        bw.flush();
        bw.close();
        br.close();
    }

    static void dp() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
    }

}
