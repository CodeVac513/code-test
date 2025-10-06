import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 2667번
    // BFS
    // 이게 BFS인지, DFS인지는 잘 모르겠으나 일단 BFS로 풀어본다.
    // 반복문으로 1이면서 방문하지 않은 좌표를 탐색 시작점으로 설정하면 된다.
    // 오답 노트)
    // 오름차순으로 정렬을 해야 한다.

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] mx = {0, 0, -1, 1};
    static int[] my = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    answer.add(bfs(i, j));
                }
            }
        }

        bw.write(String.valueOf(answer.size()));
        bw.write("\n");
        Collections.sort(answer);
        for (int houses : answer) {
            bw.write(String.valueOf(houses));
            bw.newLine();
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static int bfs(int startX, int startY) {

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startX, startY});

        visited[startX][startY] = true;
        int count = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int currentX = cur[0];
            int currentY = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextX = currentX + mx[i];
                int nextY = currentY + my[i];

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
                    continue;
                }

                if (!visited[nextX][nextY] && map[nextX][nextY] == 1) {
                    visited[nextX][nextY] = true;
                    q.add(new int[]{nextX, nextY});
                    count++;
                }
            }
        }

        return count;
    }
}
