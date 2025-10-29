import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 11403번
    // 플로이드 워셜
    // N이 100이므로, 100^3을 해도 백만이라 1초 내에 가능
    // 최단 거리를 구하는 게 아니라 이어져있는지가 궁금한 것이므로.
    // 인접 행렬 업데이트 조건을 [s][k] == 1 && [k][e] == 1로 사용해야 함.

    static int[][] distance;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        distance = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                distance[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                bw.write(distance[i][j] + " ");
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
                    if (distance[i][k] == 1 && distance[k][j] == 1) {
                        distance[i][j] = 1;
                    }
                }
            }
        }
    }
}
