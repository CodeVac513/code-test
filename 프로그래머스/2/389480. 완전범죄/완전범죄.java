import java.util.*;

class Solution {
    int[][] info;
    int n;
    int m;
    int size;
    int[][] dp;
    int INF = 40 * 3 + 1;
    
    public int solution(int[][] info, int n, int m) {
        this.info = info;
        this.size = info.length;
        this.n = n;
        this.m = m;
        
        this.dp = new int[size+1][m];
        
        for(int i = 0 ; i <= size ; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;
        
        for(int i = 1; i<= size ;i++) {
            int a = info[i-1][0];
            int b = info[i-1][1];
            
            for(int j = 0 ; j < m ; j++) {
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + a);
                
                if(j+b < m) {
                    dp[i][j+b] = Math.min(dp[i][j+b], dp[i-1][j]);
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        
        for(int i = 0 ; i < m ; i++) {
            answer = Math.min(dp[size][i], answer);
        }
        
        if(answer >= n) return -1;
        
        return answer;
    }
    
    // public int 
}

// A와 B가 물건을 훔침
// info[i][0]에는 A에 대한 흔적을 기록
// info[i][1]에는 B에 대한 흔적을 기록
// A는 n개 이상, B는 m개 이상이면 경찰에 잡힘
// => 두 도둑이 모두 붙잡히지 않을 정도로 모든 물건을 훔침, A가 남긴 흔적의 누적 개수 최솟값을 리턴하도록.
// B가 최대한 m에 가깝게 물건을 훔치도록 설계하고, 나머지를 A가 훔치면 될 듯?
// 모든 경우의 수에 대해서 일단 다 봐야 하기 때문에 DP인가?