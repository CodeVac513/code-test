import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1194번
    // 비트마스킹 + BFS
    // & - and, | - or, ^ - xor, ~ - not
    // << - left shift, 9(00001001) << 1 -> 18(00010010) 왼쪽으로 한 칸 밈, 2를 곱하는 연산으로 생각할 수 있음.
    // >> - right shift, 오른쪽으로 한 칸 밈, 2를 나누고 나머지를 버리는 연산이라고 생각해도 됨.
    // 열쇠를 가지고 가야하기 때문에, vistied 배열에서 열쇠 상태를 추적해야 함.
    // 열쇠 종류가 6개이므로, 비트마스킹을 활용하면 64가지 상태를 추적할 수 있음.

    static int[] mX = {0, 0, -1, 1};
    static int[] mY = {-1, 1, 0, 0};
    static int N;
    static int M;
    static String[][] board;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new String[N][M];
        visited = new boolean[N][M][64];
        int startX = 0, startY = 0;
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                if (board[i][j].equals("0")) {
                    startX = i;
                    startY = j;
                }
            }
        }

        int answer = bfs(startX, startY, 0, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static int bfs(int startX, int startY, int keys, int distance) {
        Queue<State> q = new LinkedList<>();

        q.offer(new State(startX, startY, keys, distance));
        visited[startX][startY][0] = true;

        while (!q.isEmpty()) {
            State current = q.poll();
            int currentX = current.x;
            int currentY = current.y;
            int currentDist = current.distance;
            int currentKeys = current.keys;

            if (board[currentX][currentY].equals("1")) {
                return currentDist;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = currentX + mX[i];
                int nextY = currentY + mY[i];
                int newKeys = currentKeys;
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M)
                    continue;

                if (board[nextX][nextY].equals("#"))
                    continue;

                char next = board[nextX][nextY].toCharArray()[0];

                if (next >= 'A' && next <= 'F') {
                    int neededKey = next - 'A';
                    // 현재 가지고 있는 key 목록 중, 필요한 key가 없다면 벽과 다름 없음. continue
                    if ((current.keys & (1 << neededKey)) == 0) {
                        continue;
                    }
                }

                if (next >= 'a' && next <= 'f') {
                    int keyIndex = next - 'a';
                    newKeys = (current.keys | (1 << keyIndex));
                }

                // 이 상태로 방문한 적이 있다면 continue;
                if (visited[nextX][nextY][newKeys])
                    continue;

                q.offer(new State(nextX, nextY, newKeys, currentDist + 1));
                visited[nextX][nextY][newKeys] = true;
            }
        }
        return -1;
    }

    static class State {
        int x, y;
        int keys;
        int distance;

        State(int x, int y, int keys, int distance) {
            this.x = x;
            this.y = y;
            this.keys = keys;
            this.distance = distance;
        }
    }
}


