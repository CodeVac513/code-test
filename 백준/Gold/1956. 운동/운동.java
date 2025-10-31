import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1956번
    // 사이클을 찾아야 한다.
    // 최소 가중치의 사이클을 찾아야 하므로, 플로이드 워셜 알고리즘이 적합하다.
    // 플로이드 워셜을 실행하며 실행 중간에 사이클을 체크한다.
    // -> k 단계마다 distance[i][i]가 업데이트되는데, 그 값이 최솟값인지 확인하는 것.
    //
    static int[][] distance;
    static int V;
    static int E;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        distance = new int[V][V];
        final int INF = 400 * 10000 + 1;
        for (int i = 0; i < V; i++) {
            Arrays.fill(distance[i], INF);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            distance[from][to] = cost;
        }

        dp();

        bw.write(String.valueOf(answer == INF ? -1 : answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static void dp() {
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
            for (int i = 0; i < V; i++) {
                answer = Math.min(distance[i][i], answer);
            }
        }
    }


}
