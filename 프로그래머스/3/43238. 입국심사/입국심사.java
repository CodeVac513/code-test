import java.util.*;

class Solution {
    // 10억명과 심사에 걸리는 시간이 10억분
    // 모든 사람이 심사를 받는데 걸리는 최솟값 -> 최적화 문제를 결정 문제로?
    // X분이면 이 사람들이 모두 통과할 수 있는지?
    // left는 times에서 제일 짧을 수 있는 1분, right는 times에서 제일 긴 10억분
    // target은 n이며 isPossible로 X분 안에 n명 이상을 받을 수 있는지 확인해야 함.
    
    // 오답 노트) 10억 단위로 놀기 때문에 count를 int로 두면 오버플로우 발생가능성 있음.
    // right 값이 잘못됨. 가장 느린 심사관 * n까지 되어야 할 수도 있음.
    // 심사관이 1명에 n명이 들어오는데, 각각 10억분 10억명일 수도 있음.
    
    public long solution(int n, int[] times) {
        long answer = binarySearch(times, n);
        return answer;
    }
    
    public long binarySearch(int[] times, int n) {
        long left = 1;
        long right = 1000000000L * 1000000000L;
        
        long answer = 0;
        while(left <= right) {
            long finishMinute = left + (right - left) / 2;

            if(isPossible(times, n, finishMinute)) {
                answer = finishMinute;
                right = finishMinute - 1;
            } else {
                left = finishMinute + 1;
            }
        }
        
        return answer;
    }
    
    public boolean isPossible(int[] times, int n, long finishMinute) {
        long count = 0;
        for(int time : times) {
            count += finishMinute / time;
        }
        
        return count >= n ? true : false;
    }
}