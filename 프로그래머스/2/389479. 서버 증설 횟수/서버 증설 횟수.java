import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] current = new int[24];
        Arrays.fill(current, 0);
        for(int i = 0 ; i < 24 ; i++) {
            if(players[i] >= m) {
                if(current[i] < players[i] / m) {
                    answer += players[i] / m - current[i];
                    for(int j = i + k - 1 ; j >= i ; j--) {
                        if(j > 23) continue;
                        current[j] += (players[i] / m) - current[i];
                    }
                }
            }
        }
        
        return answer;
    }
}

// 최대 m - 1명