import java.util.*;

class Solution {
    int[][] land;
    boolean[][] visited;
    int[] moveX = {0,0,-1,1};
    int[] moveY = {-1,1,0,0};
    int landRow;
    int landCol;
    Map<Integer, Integer> map;
    Set<Integer> set;
    
    public int solution(int[][] land) {
        int answer = 0;
        
        landRow = land.length;
        landCol = land[0].length;
        
        this.land = land;
        visited = new boolean[landRow][landCol];
        map = new HashMap();
        int id = 3;
        for(int i = 0 ; i < landRow ; i++) {
            for(int j = 0 ; j < landCol ; j++) {
                if(land[i][j] == 1 && !visited[i][j]) {
                    int count = bfs(i, j, id);
                    map.put(id, count);
                    id++;
                }
            }
        }
        
        for(int i = 0 ; i < landCol ; i++) {
            int count = 0;
            set = new HashSet();
            for(int j = 0 ; j < landRow ; j++) {
                if(land[j][i] != 0 && set.add(land[j][i])) {
                    count += map.get(land[j][i]);
                } 
            }
            
            answer = Math.max(answer, count);
        }
        return answer;
    }
    
    public int bfs(int startX, int startY, int id) {
        int count = 0;
        Queue<Integer[]> q = new LinkedList();
        visited[startX][startY] = true;
        land[startX][startY] = id;
        q.offer(new Integer[] {startX,startY});

        while(!q.isEmpty()) {
            Integer[] current = q.poll();
            int currentX = current[0];
            int currentY = current[1];
            count++;
            
            for(int i = 0 ; i < 4 ; i++) {
                int nextX = currentX + moveX[i];
                int nextY = currentY + moveY[i];
                
                if(nextX < 0 || nextY < 0 || nextX >= landRow || nextY >= landCol) continue;
                
                if(visited[nextX][nextY]) continue;
                
                if(land[nextX][nextY] == 1) {
                    visited[nextX][nextY] = true;
                    land[nextX][nextY] = id;
                    q.offer(new Integer[] {nextX, nextY});
                }
            }
        }
        
        return count;
    }
}

// 열마다 bfs를 통해서 면적을 구한 뒤에 더하는 방법은 어떨까?
// 예를 들어 (1,1), (2,1), (3,1) ... 1에서 시작하는 모든 것들을 더하고 visited를 통해서 방문한 곳은 방문 x
// 다음으로 visited를 초기화하고, (1,2), (2,2), (3,2)... 방문...
// O(m * n * m) = O(n * m^2) => 최악의 경우 이런 값이 나옴
// 미리 BFS를 돌려서 석유에 ID를 부여하고, 열을 뚫을 때 마주치는 ID를 통해 미리 계산된 매장량을 더해줄 수 있음.
// 그렇게 계산하면 O(m * n)으로 계산 가능