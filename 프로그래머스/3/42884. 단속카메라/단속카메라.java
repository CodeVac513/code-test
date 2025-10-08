import java.util.*;

class Solution {
    // 그리디적 사고 기르기, 활동 선택 문제(Activity Selection Problem)
    // 끝점을 기준으로 정렬해서 가장 빨리 나가는 자동차를 체크하면?
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (a,b) -> {
           return a[1] - b[1];
        });
        
        answer = greedy(routes);
        
        return answer;
    }
    
    public int greedy(int[][] routes) {
        int count = 1;
        int lastCamera = routes[0][1];
        for(int[] route : routes) {
            if(lastCamera < route[0]) {
                lastCamera = route[1];
                count++;
            }
        }
        return count;
    }
}