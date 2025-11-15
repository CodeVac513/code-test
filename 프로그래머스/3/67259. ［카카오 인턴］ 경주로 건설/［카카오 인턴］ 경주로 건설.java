import java.util.*;

class Solution {
    // 입력: N * N의 정사각형 격자
    // 0은 비어 있고, 1은 지나갈 수 없는 벽
    // (0,0)에서 출발하고 도착점은 (N-1,N-1)
    // 0 0 -> N-1 N-1로 가는 경주로를 건설해야 함.
    // 상하좌우로 인접한 두 빈 칸만 연결할 수 있음.
    // 상하 혹은 좌우로 연결된 도로는 직선 - 100원 소요
    // 도로가 직각으로 만나는 경우 코너 - 500원 소요
    // 경주로를 건설하는 최소 비용 
    // 상태를 추적해야 함. -> 상하로 움직이다가 좌우로 움직이는 순간 500원이 더 들어감.
    // 최소 경로는 BFS를 통해서, 상태 추적은 DP?
    
    int[][] board;
    int[] moveX = {0, 0, -1, 1};
    int[] moveY = {-1, 1, 0, 0};
    int[][][] cost;
    int N;
    final int INF = 25 * 25 * 700 + 1;
    
    public int solution(int[][] board) {

        this.board = board;
        this.N = board.length;
        // 이전 도로가 상하인 경우는 0, 좌우인 경우는 1로 설정
        this.cost = new int[N][N][2];
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                cost[i][j][0] = INF;
                cost[i][j][1] = INF;
            }
        }
        
        bfs();
        
        
        return Math.min(cost[N-1][N-1][0], cost[N-1][N-1][1]);
    }
    
    public void bfs() {
        Queue<int[]> q = new LinkedList();
        q.offer(new int[] {0, 0, -1});
        
        while(!q.isEmpty()) {
            int[] current = q.poll();
            int currentX = current[0];
            int currentY = current[1];
            int dir = current[2];
            
            for(int i = 0 ; i < 4 ; i++) {
                int nextX = currentX + moveX[i];
                int nextY = currentY + moveY[i];
                
                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;
                
                if(board[nextX][nextY] == 1) continue;
                                
                if(dir == -1) {
                    if(i < 2) {
                        cost[nextX][nextY][0] = 100;
                        q.offer(new int[]{nextX, nextY, 0});
                    } else {
                        cost[nextX][nextY][1] = 100;
                        q.offer(new int[] {nextX, nextY, 1});
                    }
                } else if(dir == 0) {
                    if(i < 2) {
                        int newCost = cost[currentX][currentY][0] + 100;
                        if (cost[nextX][nextY][0] > newCost) {
                            cost[nextX][nextY][0] = newCost;
                            q.offer(new int[] {nextX, nextY, 0});
                        }
                    } else {
                        int newCost = cost[currentX][currentY][0] + 600;
                        if (cost[nextX][nextY][1] > newCost) {
                            cost[nextX][nextY][1] = newCost;
                            q.offer(new int[] {nextX, nextY, 1});
                        }
                    }
                } else {
                    if(i < 2) {
                        int newCost = cost[currentX][currentY][1] + 600;
                        if (cost[nextX][nextY][0] > newCost) {
                            cost[nextX][nextY][0] = newCost;
                            q.offer(new int[] {nextX, nextY, 0});
                        }
                    } else {
                        int newCost = cost[currentX][currentY][1] + 100;
                        if (cost[nextX][nextY][1] > newCost) {
                            cost[nextX][nextY][1] = newCost;
                            q.offer(new int[] {nextX, nextY, 1});
                        }
                    }
                }
            }
        
        }
    }
}