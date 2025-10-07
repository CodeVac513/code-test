import java.util.*;

class Solution {
    // people 배열을 내림차순으로 정렬
    // 무거운 사람의 무게를 먼저 limit에서 빼고 사람을 더 태울 수 있는지 확인하면 가장 움직임을 적게 할 수 있음.
    // 이 때, 제일 무거운 사람은 제일 가벼운 사람 + a와 나가면 됨.
    // 무거운 사람과 가벼운 사람을 투포인터를 활용해서 컨트롤할 수 있을 것 같다.
    // 오답 노트 ) 무거운 사람 + 여유가 있으면 가벼운 사람 여러 명이 될 줄 알았는데, 문제 조건에서 2명만 탈 수 있다고 못 박음.
    public int solution(int[] people, int limit) {
        int answer = 0;
        Integer[] peopleArray = new Integer[people.length];
        for(int i = 0 ; i < people.length ; i++) {
            peopleArray[i] = people[i];
        }
        
        Arrays.sort(peopleArray, Collections.reverseOrder());
        
        answer = greedy(peopleArray, limit);
        
        return answer;
    }
    
    public int greedy(Integer[] people, int limit) {
        int n = people.length;
        int heavyPoint = 0;
        int lightPoint = n - 1;
        int sum = calculateWeightSum(people);
        int count = 0;
        while(sum > 0) {
            int currentWeight = people[heavyPoint];
            heavyPoint++;
            if(currentWeight + people[lightPoint] <= limit) {
                currentWeight += people[lightPoint];
                lightPoint--;
            }
            sum -= currentWeight;
            count++;
        } 
        
        return count;
    }
    
    public int calculateWeightSum(Integer[] people) {
        return Arrays.stream(people).reduce(0, (a, b) -> a + b);
    }
}