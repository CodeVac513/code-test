import java.util.*;

class Solution {
    // 너무 어려워서 클로드에게 힌트를 얻었다.
    // N을 i개 사용해서 만들 수 있는 모든 수를 Set에 저장한다.
    // dp[j]와 dp[i-j]를 조합해서 새로운 Set을 만들어낸다.
    // 연산의 종류는 String 이어붙이기, 사칙연산 정도가 있을 듯
    Set<Integer>[] dp;
    public int solution(int N, int number) {
        
        dp = new HashSet[9];
        
        for(int i = 0 ; i <= 8 ; i++) {
            dp[i] = new HashSet();
        }
        
        for(int i = 1 ; i <= 8 ; i++) {
            // N이 연속된 숫자를 Set에 삽입
            dp[i].add(Integer.parseInt(String.valueOf(N).repeat(i)));
            
            // dp[j] ㅁ dp[i-j] => ㅁ은 사칙연산 중 아무거나...
            for(int j = 1; j < i ; j++) {
                for(int num1 : dp[j]) {
                    for(int num2 : dp[i-j]) {
                        dp[i].add(num1 + num2);
                        dp[i].add(num1 - num2);
                        dp[i].add(num1 * num2);
                        if(num2 != 0) {
                            dp[i].add(num1 / num2);
                        }
                    }
                }
            }
            if(dp[i].contains(number)) return i;
        }
        
        return -1;
    }
}