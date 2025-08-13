import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 11123번
    // 완전 탐색
    // #(양)이 1개 이상 상하좌우로 붙어있는 경우를 무리로 정의 -> #으로 시작하는데, Visited가 false인 위치에서 #으로 연결된 부분을 모두 방문하고 visited를 true 처리한다.
    // 반복문을 통해 #의 위치에서 DFS를 시작한다.

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testIterations = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < testIterations; testCase++) {
            String[] input = br.readLine().split(" ");
            int H = Integer.parseInt(input[0]);
            int W = Integer.parseInt(input[1]);

            String[][] map = new String[H][W];

            Boolean[][] visited = new Boolean[H][W];
            for (int i = 0; i < H; i++) {
                Arrays.fill(visited[i], false);
            }

            for (int i = 0; i < H; i++) {
                map[i] = br.readLine().split("");
            }

            //dfs
            int result = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    count = 0;
                    dfs(i, j, map, visited, H, W);
                    if(count >= 1) {
                        result++;
                    }
                }
            }

            bw.write(result + "\n");

        }

        br.close();
        bw.flush();
        bw.close();
    }

    static void dfs(int startX, int startY, String[][] map, Boolean[][] visited, int height, int width) {
        if (visited[startX][startY]) {
            return;
        }
        visited[startX][startY] = true;

        if(map[startX][startY].equals("#")) {
            count++;
        } else {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = startX + dx[i];
            int nextY = startY + dy[i];

            if(nextX >= 0 && nextX < height && nextY >= 0 && nextY < width) {
                if(!visited[nextX][nextY] && map[nextX][nextY].equals("#")) {
                    dfs(nextX, nextY, map, visited, height, width);
                }
            }
        }
    }
}