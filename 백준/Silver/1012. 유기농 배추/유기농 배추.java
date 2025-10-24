import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1012
    // DFS, BFS
    // 영역마다 지렁이 한 마리가 필요하다.
    // 따라서 배추가 이어진 영역이 몇 개가 있는지 찾아봐야 한다.
    // visited가 true인 지점은 지나치고, false인 지점에서 DFS/BFS를 시도하여 지역을 하나 찾는다.
    // 그렇게 모든 지점을 탐색해서 몇 번의 탐색을 시도했는지 체크를 하면 지렁이의 최소 마리수를 얻을 수 있다.

    static int[][] map;
    static boolean[][] visited;

    static int[] moveX = {0, 0, -1, 1};
    static int[] moveY = {-1, 1, 0, 0};
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int answer = 0;
            map = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j]) continue;
                    if (map[i][j] == 1) {
                        bfs(i, j);
                        answer++;
                    }
                }
            }

            bw.write(answer + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static void bfs(int startX, int startY) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextX = x + moveX[i];
                int nextY = y + moveY[i];
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
                if (visited[nextX][nextY]) continue;
                if (map[nextX][nextY] == 1) {
                    visited[nextX][nextY] = true;
                    q.add(new int[]{nextX, nextY});
                }
            }
        }
    }
}
