import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 11404번
    // 플로이드 워셜
    // 모든 노드 사이의 최단 경로를 구해야 하기 때문에, 다익스트라가 아니라 플로이드 워셜을 활용한다.
    // 다익스트라는 그리디, 플로이드 워셜은 DP에 기반한다.
    // O(V^3)의 시간복잡도로, 이 문제에서는 100^3 = 10^6 이므로, 1초 내에 연산 가능
    // 가장 핵심적인 원리는 Start -> End가 최단 거리일 때,
    // Start -> K -> End 중 Start -> K와 K -> End 또한 최단 경로이다.
    // 그래서 점화식은 DP[S][E] = Math.min(DP[S][E], DP[S][K] + DP[K][E])가 된다.
    // DP를 활용하기 하려면 인접 행렬이 인접 리스트보다 자연스러울 수 있음.
    // 근데 이 문제는 음수 가중치가 없으니까, 다익스트라를 N번 반복하면 플로이드 워셜 알고리즘이 아니더라도 답이 나올수도?

    // 플로이드 워셜의 탬플릿 정리
    // 1. 인접 행렬에 정보가 저장되고 최소 거리가 업데이트 된다.
    // 2. 3중 반복문 for 경유지 K -> for 시작지점 S -> for 끝나는 지점 E 순으로 사용
    // 3. 점화식은 [s][e] = [s][k] + [k][e];

    static int[][] distance;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        distance = new int[n][n];
        final int INF = Integer.MAX_VALUE / 2;
        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], INF);
            distance[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            if (cost < distance[start][end]) {
                distance[start][end] = cost;
            }
        }

        dp();

        for (

                int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (distance[i][j] == INF) {
                    bw.write("0 ");
                } else {
                    bw.write(distance[i][j] + " ");
                }
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void dp() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
    }
}
