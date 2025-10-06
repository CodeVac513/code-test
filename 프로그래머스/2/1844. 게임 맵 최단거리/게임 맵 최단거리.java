import java.util.*;
import java.lang.*;

class Solution {
    // BFS 최단거리 구하기 문제
    // distance 배열이 핵심
    
    int[][] distance;
    boolean[][] visited;
    int[] mx = {0, 0, -1, 1};
    int[] my = {1, -1, 0, 0};
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        distance = new int[n][m];
        visited = new boolean[n][m];
        
        //도달 못할 가능성을 대비해서 미리 -1로 초기화
        distance[n - 1][m - 1] = -1;
        
        bfs(maps, new int[] {0,0}, n, m);
        
        
        return distance[n - 1][m - 1];
    }
    
    public void bfs(int[][] maps, int[] startNode, int n, int m) {
        Queue<int[]> q = new LinkedList();
        
        visited[startNode[0]][startNode[1]] = true;
        distance[startNode[0]][startNode[1]] = 1;
        q.offer(startNode);
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int currentX = cur[0];
            int currentY = cur[1];
            
            for(int i = 0 ; i < 4 ; i++) {
                int nextX = currentX + mx[i];
                int nextY = currentY + my[i];
                
                if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue;
                                
                if(!visited[nextX][nextY] && maps[nextX][nextY] == 1) {
                    visited[nextX][nextY] = true;
                    distance[nextX][nextY] = distance[currentX][currentY] + 1;
                    q.offer(new int[] {nextX, nextY});
                }
            }
        }
    }
}