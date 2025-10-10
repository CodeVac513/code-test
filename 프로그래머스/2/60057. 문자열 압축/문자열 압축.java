import java.util.*;


class Solution {
//     <입력>
//         1이상, 1000이하 길이의 문자열
//     <출력>
//         가장 짧은 압축된 문자열의 길이
    
//     1. 1부터 1000이하의 토큰 길이로 문자열 압축을 시도한다.
//     - 같은 문자열이 연속되는 경우에만 숫자를 센다. (다른 문자열을 사이에 두고 반복되면 따로 셈)
//     - 1개만 있으면 숫자를 생략한다.
//     2. 해당 토큰 길이가 가장 짧을 때 변수를 업데이트한다.
//     3. 토큰 길이를 반환한다.
    public int solution(String s) {
        int answer = getAnswer(s);
        return answer;
    }
    
    public int getAnswer(String s) {
        int lengthLimit = s.length();
        int min = lengthLimit == 1 ? 1 : Integer.MAX_VALUE;
        for(int i = 1 ; i < lengthLimit ; i++) {
            int currentLength = zipString(s, i);
            min = Math.min(min, currentLength);
        }
        
        return min;
    }
    
    public int zipString(String target, int tokenSize) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        String prevToken = target.substring(0, tokenSize);
        for(int i = tokenSize ; i < target.length() ; i += tokenSize) {
            if(tokenSize + i > target.length()) {
                String currentToken = target.substring(i);
                if(prevToken.equals(currentToken)) {
                    count++;
                } else {
                    if(count > 1) {
                        sb.append(count);
                    }
                    sb.append(prevToken);
                    count = 1;
                    prevToken = currentToken;
                }
                break;
            }
            
            String currentToken = target.substring(i, tokenSize + i);
            
            if(prevToken.equals(currentToken)) {
                count++;
            } else {
                if(count > 1) {
                    sb.append(count);
                }
                sb.append(prevToken);
                count = 1;
            }
            prevToken = currentToken;
        }
        
        if(count > 1) {
            sb.append(count);
        }
        sb.append(prevToken);
                
        return sb.toString().length();
    }
}