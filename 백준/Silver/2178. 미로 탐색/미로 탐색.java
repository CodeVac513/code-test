import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 2178번
    // BFS
    // 경로의 최소 거리 -> BFS
    // 오답 노트) 
    // 거리를 측정하기 위해서는 distance 배열을 만들어서 사용해야 함. 
    // count 변수를 놓고 방문한 노드를 세는 것과는 다른 문제임!
    static int N;
    static int M;
    static int[][] maze;
    static int[][] distance;
    static int[] mx = {0, 0, -1, 1};
    static int[] my = {1, -1, 0, 0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        distance = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            maze[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        bw.write(String.valueOf(bfs(0, 0)));

        br.close();
        bw.flush();
        bw.close();
    }

    static int bfs(int startX, int startY) {
        Queue<Integer> xQueue = new LinkedList<>();
        Queue<Integer> yQueue = new LinkedList<>();
        xQueue.offer(startX);
        yQueue.offer(startY);
        visited[startX][startY] = true;
        distance[startX][startY] = 1;
        while (!xQueue.isEmpty() && !yQueue.isEmpty()) {
            int currentX = xQueue.poll();
            int currentY = yQueue.poll();

            if (currentX == N - 1 && currentY == M - 1) {
                return distance[currentX][currentY];
            }

            for (int i = 0; i < 4; i++) {
                int nextX = currentX + mx[i];
                int nextY = currentY + my[i];
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                    continue;
                }

                if (!visited[nextX][nextY] && maze[nextX][nextY] == 1) {
                    visited[nextX][nextY] = true;
                    distance[nextX][nextY] = distance[currentX][currentY] + 1;
                    xQueue.offer(nextX);
                    yQueue.offer(nextY);
                }
            }
        }
        return -1;
    }
}
