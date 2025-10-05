import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        int totalUsers = stages.length;
        countClear(countMap, stages);
        Stage[] calculatedStages = calculateFailureRate(countMap, N, totalUsers);
        Arrays.sort(calculatedStages, (a, b) -> {
            if(a.failureRate == b.failureRate) {
                return a.stageNum - b.stageNum;
            }
            return Double.compare(b.failureRate, a.failureRate);
        });
        int[] answer = new int[N];
        for(int i = 0 ; i < N ; i++) {
            answer[i] = calculatedStages[i].stageNum;
        }
        return answer;
    }
    
    public Stage[] calculateFailureRate(HashMap<Integer, Integer> countMap, int N, int totalUsers) {
        Stage[] calculatedStages = new Stage[N];
        for(int i = 1 ; i <= N; i++) {
            double failureRate;
            if(totalUsers == 0) {
                failureRate = 0;
            } else {
                failureRate = (double) countMap.getOrDefault(i, 0) / (double) totalUsers;
            }
            calculatedStages[i - 1] = new Stage(i, failureRate);
            totalUsers -= countMap.getOrDefault(i, 0);
        }
        
        return calculatedStages;
    }
    
    public void countClear(HashMap<Integer, Integer> countMap, int[] stages) {
        for(int currentStage : stages) {
            countMap.put(currentStage, countMap.getOrDefault(currentStage, 0) + 1);
        }
    }
    
    class Stage {
        int stageNum;
        double failureRate;
        
        Stage(int stageNum, double failureRate) {
            this.stageNum = stageNum;
            this.failureRate = failureRate;
        }
    }
}