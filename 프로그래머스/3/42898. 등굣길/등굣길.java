class Solution {
    
    private final int DIVISION_NUM = 1000000007;
    int m;
    int n;
    int[][] puddles;
    int[][] dp;
    int[] mx = {0, 0, -1, 1};
    int[] my = {1, -1, 0, 0};
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        this.m = m - 1;
        this.n = n - 1;
        this.puddles = puddles;
        dp = new int[m + 1][n + 1];
        
        dp[1][1] = 1;
        
        for(int[] puddle : puddles) {
            dp[puddle[0]][puddle[1]] = -1;
        }
        
        for(int i = 1 ; i <= m ; i++) {
            for(int j = 1; j <= n ; j++) {
                if(i == 1 && j == 1) {
                    continue;
                }
                
                if(dp[i][j] == -1) {
                    continue;
                }
                
                if(dp[i-1][j] == -1 && dp[i][j-1] == -1) {                    
                    continue;
                }
                
                if(dp[i-1][j] == -1) {    
                    dp[i][j] = dp[i][j-1];
                    continue;
                }
                
                if(dp[i][j-1] == -1) {       
                    dp[i][j] = dp[i-1][j];
                    continue;
                }
                
                dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % DIVISION_NUM;
            }
        }
        
        answer = dp[m][n];
        
        return answer;
    }
}