import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        
        while(sb.length() < t * m) {
            sb.append(convertInteger(num, n));
            num++;
        }
        
        StringBuilder answer = new StringBuilder();
        for (int i = 0 ; i < t ; i++) {
            answer.append(sb.charAt(p - 1 + i * m));
        }
        return answer.toString();
    }
    
    public String convertInteger(int num, int n) {
        if(num == 0)
            return "0";
        
        String digits = "0123456789ABCDEF";
        StringBuilder sb = new StringBuilder();
        
        while (num > 0) {
            sb.append(digits.charAt(num % n));
            num /= n;
        }
        
        return sb.reverse().toString();
    }
}