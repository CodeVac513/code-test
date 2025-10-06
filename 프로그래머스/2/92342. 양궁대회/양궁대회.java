class Solution {
    int[] answer = new int[11];
    int[] result = new int[11];
    int maxDiff = 0;
    public int[] solution(int n, int[] info) {
        backtracking(n, info, 0);
        if(maxDiff == 0) {
            return new int[] {-1};
        } else {
            return answer;
        }
    }
    
    public void backtracking(int n, int[] info, int depth) {
        if(depth == 11) {
            result[10] += n;
            // 승자 비교
            int ryanScore = 0, infoScore = 0;
            
            for(int i = 0 ; i < 11 ; i++) {
                if(info[i] == 0 && result[i] == 0) continue;
                if(result[i] > info[i]) {
                    ryanScore += (10 - i);
                } else {
                    infoScore += (10 - i);
                }
            }
            
            int diff = ryanScore - infoScore;
            
            if(diff > 0 && diff >= maxDiff) {
                if(diff > maxDiff || isLowerScoreMore(result, answer)) {
                    maxDiff = diff;
                    answer = result.clone();
                }
            }
            
            result[10] -= n;
            return;
        }
        
        if(n > info[depth]) {
            result[depth] = info[depth] + 1;
            backtracking(n - result[depth], info, depth + 1);
            result[depth] = 0;
        }
        
        backtracking(n - result[depth], info, depth + 1);
    }
    
    private boolean isLowerScoreMore(int ryan[], int[] answer) {
        for (int i = 10 ; i >= 0 ; i--) {
            if(ryan[i] != answer[i]) {
                return ryan[i] > answer[i];
            }
        }
        return false;
    }

}