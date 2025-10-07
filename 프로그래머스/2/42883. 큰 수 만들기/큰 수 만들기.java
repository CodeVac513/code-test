import java.util.*;
import java.lang.*;

class Solution {
    // Stack을 활용해서 문제를 풀 수 있다.
    // 각 숫자를 왼쪽부터 잘라서 push
    // 이전의 모든 숫자가 다음 숫자보다 큰지를 판별해야 함.
    // 만약 113이라고 가정하면, 
    // 3을 확인할 때, peek인 1이 3보다 작음 -> pop
    // 다시 확인하면 peek가 1이고 3보다 작음 -> pop 
    public String solution(String number, int k) {
        return greedy(number, k);
    }
    
    public String greedy(String number, int k) {
        Stack<Character> stack = new Stack();
        int n = number.length();
        
        //숫자를 하나씩 검사한다.
        for(int i = 0 ; i < n ; i++) {
            Character currentNum = number.charAt(i);
            while (!stack.isEmpty() && k > 0 && stack.peek() < currentNum) {
                stack.pop();
                k--;
            }
            stack.push(currentNum);
        }
        
        while(k > 0) {
            stack.pop();
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        int iter = stack.size();
        for(int i = 0 ;i < iter ; i++) {
            sb.append(stack.pop());
        }
        
        return sb.reverse().toString();
    }
}