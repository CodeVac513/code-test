import java.util.*;
import java.lang.*;

class Solution {
    int shortest = Integer.MAX_VALUE;
    public int solution(String s) {
        recursion(s, 1);
        return shortest;
    }
    
    public void recursion(String input, int N) {
        if(N == input.length()) {
            shortest = shortest > N ? N : shortest;
            return;
        }
        
        String result = zip(split(input, N));
        shortest = shortest > result.length() ? result.length() : shortest;
        recursion(input, N + 1);
    }
    
    public Queue<String> split(String input, int N) {
        Queue<String> tokenQueue = new LinkedList();
        while(input.length() > N) {
            String token = input.substring(0, N);
            tokenQueue.offer(token);
            input = input.substring(N);
        }
        
        tokenQueue.offer(input);
        return tokenQueue;
    }
    
    public String zip(Queue<String> tokenQueue) {
        String prevToken = tokenQueue.poll();
        String currentToken = "";
        int count = 1;
        StringBuilder sb = new StringBuilder();
        while(!tokenQueue.isEmpty()) {
            currentToken = tokenQueue.poll();
            if(prevToken.equals(currentToken)) {
                count++;
            } else if(!prevToken.equals(currentToken)){
                if(count == 1) {
                    sb.append(prevToken);
                } else {
                    sb.append(count).append(prevToken);
                }
                count = 1;
                prevToken = currentToken;
            }
        }
        if(tokenQueue.isEmpty()) {
            if(count == 1) {
                sb.append(prevToken);
            } else {
                sb.append(count).append(prevToken);
            }
            count = 1;
            prevToken = currentToken;
        }
        
        return sb.toString();
    }
}