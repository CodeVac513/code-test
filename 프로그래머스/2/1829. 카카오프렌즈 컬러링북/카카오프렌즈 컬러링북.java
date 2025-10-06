import java.util.*;
import java.lang.*;

class Solution {
    // 풀이
    // BFS로 같은 영역의 크기를 구해야 함.
    // 비슷한 문제가 있었는데... 백준의 아파트 단지 구하는 문제?
    // BFS를 여러 번 돌면서 해당 영역의 크기와 영역 탐색을 들어가면서 count를 증가시키면 풀 수 있을 듯?
    boolean[][] visited;
    int[] mx = {0, 0, -1, 1};
    int[] my = {1, -1, 0, 0};
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];
         
        for(int i = 0 ; i < m ; i++) {
            for (int j = 0 ; j < n ; j++) {
                if(!visited[i][j] && picture[i][j] != 0) {
                    int currentAreaSize = bfs(picture, new int[] {i, j}, m, n);
                    maxSizeOfOneArea = maxSizeOfOneArea < currentAreaSize ? currentAreaSize : maxSizeOfOneArea;
                    numberOfArea++;
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public int bfs(int[][] picture, int[] startNode, int m, int n) {
        Queue<int[]> q = new LinkedList();
        q.offer(startNode);
        visited[startNode[0]][startNode[1]] = true;
        int count = 1;
        int currentColor = picture[startNode[0]][startNode[1]];
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int currentX = cur[0];
            int currentY = cur[1];
            
            for(int i = 0 ; i < 4 ; i++) {
                int nextX = currentX + mx[i];
                int nextY = currentY + my[i];
                
                if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) continue;
                
                if(!visited[nextX][nextY] && picture[nextX][nextY] == currentColor) {
                    visited[nextX][nextY] = true;
                    q.offer(new int[] {nextX, nextY});
                    count++;
                }
            }
        }
        
        return count;
    }
}