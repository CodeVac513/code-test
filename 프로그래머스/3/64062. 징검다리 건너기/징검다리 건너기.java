import java.util.*;

class Solution {
    // stone의 개수는 20만개 이하
    // stone의 최대값은 2억 이하
    // 한 번에 건너뛸 수 있는 디딤돌의 최대 칸 수 k가 주어짐.
    // 최대 몇 명까지 징검다리를 건널 수 있는지 -> 최적화 문제를 결정 문제로 끌어내려야 함.
    // X명이 징검다리를 건널 수 있는가? -> 바이너리 서치(파라메트릭 서치)
    // 찾는 값은 X이고, left와 right 값을 정의해야 함.
    // left와 right는 밟은 횟수, 밟은 횟수 그 자체가 사람이 몇 명 넘어갔는지를 의미함. (가장 가까운 돌만 밟을 수 있기 때문)
    // 밟은 횟수를 통해서 돌의 길이가 k가 넘어가는지 안 넘어가는지 봐야 함.
    // K는 폴짝의 횟수임. 0이 3번 연속되면 폴짝 4번으로 넘어가야 하기에 k = 3인 경우, 건널 수 없음.
    public int solution(int[] stones, int k) {
        int answer = binarySearch(stones, k);
        return answer;
    }
    
    public int binarySearch(int[] stones, int k) {
        int left = 1;
        int right = Arrays.stream(stones).max().getAsInt();
        int answer = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if(isPossible(stones, k, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }
    
    public boolean isPossible(int[] stones, int k, int people) {
        int count = 0;
        for(int stone : stones) {
            if(stone < people) {
                count++;
                if(count >= k) {
                    return false;
                }
            } else {
                count = 0;
            }
        }
        return true;
    }
}