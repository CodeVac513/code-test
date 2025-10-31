import java.util.*;

class Solution {
    // n명의 권투선수 -> Node가 n개
    // 실력 차이가 나면 항상 그 실력대로 결과가 난다. 그래서 주어진 경기 결과를 통해 역으로 
    // 선수의 실력을 추정해야 한다. -> A선수가 B 선수보다 실력이 좋다면 항상 이길 것이고, 가중치가 1로 설정된 방향 그래프에서 A -> B로 이동하는 것과 같다.
    // 플로이드 워셜을 활용해서 연결되어 있는 Node가 없어서 INF 값으로 초기화된 부분이 남아 있다면 그 선수의 순위를 매길 수 없다고 해석할 수 있다.
    // (한 선수에 대해 내 앞에 몇 명 있는지, 내 뒤에 몇 명 있는지를 모두 알아야 한다는 의미)
    
    int[][] distance;
    int n;
    final int INF = 100 * 4500 + 1;
    public int solution(int n, int[][] results) {
        int answer = 0;
        this.n = n;
        this.distance = new int[n][n];
        
        for(int i = 0 ; i < n ; i++) {
            Arrays.fill(distance[i], INF);
            distance[i][i] = 0;
        }
        
        for(int i = 0 ; i < results.length ; i++) {
            int[] info = results[i];
            int A = info[0] - 1;
            int B = info[1] - 1;
            distance[A][B] = 1;
        }
        
        dp();
       
        for(int i = 0 ; i < n ; i++) {
            boolean isWinned = true;
            for(int j = 0 ; j < n ; j++) {
                if(i == j) continue;
                if(distance[i][j] >= INF && distance[j][i] >= INF) {
                    // 둘 모두 INF인 경우는 이겼는지 졌는지 알 수 없음.
                    // 하지만 한 쪽만 INF인 경우는 어느 쪽이 졌는지 알 수 있음.
                    isWinned = false;
                    break;
                }
            }
            if(isWinned) answer++;
        }
        
        return answer;
    }
    
    void dp() {
        for(int k = 0 ; k < n ; k++) {
            for(int i = 0 ; i < n ; i++) {
                for(int j = 0 ; j < n ; j++) {
                    if(distance[i][j] > distance[i][k] + distance[k][j])
                        distance[i][j] = distance[i][k] + distance[k][j];
                }
            }
        }
    }
}