class Solution {
    // 그리디, 비어있는 구간을 2*w + 1의 구간으로 커버해야 함.
    // 그 비어있는 구간은 '시작 지점 ~ station이 설치된 곳 - w'까지임.
    // station이 설치된 곳을 지나면 (station + w) + 1가 시작지점이 됨.
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int start = 1;  
        
        for(int station : stations) {
            int leftSide = station - w;  
            
            if (start < leftSide) {
                int emptyLength = leftSide - start;
                answer += Math.ceil((double)emptyLength / (2*w + 1));
            }
            
            start = station + w + 1;  
        }
        
        if (start <= n) {
            int emptyLength = n - start + 1;
            answer += Math.ceil((double)emptyLength / (2*w + 1));
        }
        
        return answer;
    }
}