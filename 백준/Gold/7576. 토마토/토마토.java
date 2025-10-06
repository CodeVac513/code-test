import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 7576번
    // 완전 탐색, BFS
    // 최소 일수를 구해야한다.
    // -> 인접한 녀석들이 익는데 하루가 걸림, 그러면 가장 멀리있는 녀석(도착지점)까지 최단거리를 구하는 것과 같음.
    // 최단거리를 구한 뒤, 전체 탐색을 한 번 더 해보고 0이 있으면 -1을 출력.
    // (어차피 10^6이라 시간 초과는 안걸릴듯)
    // 오답노트 1)
    // 처음부터 다 숙성되어 있는 토마토가 걸리면 0일로 출력해야 함.
    // 오답노트 2)
    // 시작점이 여러 개일 수 있음. 그래서 visited가 true일 때 방문하고 false일 때 방문하지 않는, 반대로 동작하는 녀석도 구현해야 함.
    // 오답노트 3)
    // 시간초과 발생 -> 뒤집는 발상은 재밌었지만, 잘못됨.
    // startNode를 모두 Queue나 Array 등으로 받아서 한꺼번에 탐색을 진행해야 함.
    // (각각의 시작점이 같은 레벨의 노드라고 생각하면 이해하기 쉬움)
    //
    static int N;
    static int M;
    static int[][] map;
    static int[][] distance;
    static boolean[][] visited;
    static int[] mx = {0, 0, -1, 1};
    static int[] my = {1, -1, 0, 0};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        map = new int[N][M];
        distance = new int[N][M];
        Queue<int[]> startNodeQueue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    startNodeQueue.offer(new int[]{i, j});
                }
            }
        }

        bfs(startNodeQueue);

        if (!checkTomato()) {
            bw.write("-1\n");
        } else {
            bw.write(String.valueOf(answer));
        }
        br.close();
        bw.flush();
        bw.close();
    }

    static boolean checkTomato() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (distance[i][j] == 0 && map[i][j] == 0) {
                    return false;
                }
                if (distance[i][j] > answer) {
                    answer = distance[i][j];
                }
            }
        }
        return true;
    }

    static void bfs(Queue<int[]> startNodeQueue) {


        Queue<int[]> q = new LinkedList<>();
        for (int[] startNode : startNodeQueue) {
            q.add(startNode);
            visited[startNode[0]][startNode[1]] = true;
            distance[startNode[0]][startNode[1]] = 0;
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int currentX = cur[0];
            int currentY = cur[1];


            for (int i = 0; i < 4; i++) {
                int nextX = currentX + mx[i];
                int nextY = currentY + my[i];

                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                    continue;
                }

                if (map[nextX][nextY] == -1) {
                    distance[nextX][nextY] = -1;
                }

                if (!visited[nextX][nextY] && map[nextX][nextY] == 0) {
                    visited[nextX][nextY] = true;
                    q.add(new int[]{nextX, nextY});
                    if (distance[nextX][nextY] == 0) {
                        distance[nextX][nextY] = distance[currentX][currentY] + 1;
                    } else if (distance[nextX][nextY] > distance[currentX][currentY] + 1) {
                        distance[nextX][nextY] = distance[currentX][currentY] + 1;
                    }
                }
            }
        }
    }
}
