import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1389번
    // 플로이드 워셜
    // N이 작고 모든 경우에 대해서 최소 값을 찾아야 한다.
    // 케빈 베이컨 수는 자신을 제외하고, 남들과의 최단 경로의 합과 같다.


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
        final int INF = 5000 * 100 + 1;
        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], INF);
            distance[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            distance[start][end] = 1;
            distance[end][start] = 1;
        }

        dp();
        int smallest = Integer.MAX_VALUE;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int num : distance[i]) {
                sum += num;
            }
            if (smallest > sum) {
                answer = i;
                smallest = sum;
            }
        }

        bw.write(String.valueOf(answer + 1));
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
