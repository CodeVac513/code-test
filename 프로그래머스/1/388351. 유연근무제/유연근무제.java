class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        for(int i = 0 ; i < schedules.length ; i++) {
            int count = 0;
            for(int j = 0 ; j < 7 ; j++) {
                int currentDay = (startday + j - 1) % 7 + 1;
                if(currentDay == 6 || currentDay == 7) {
                    continue;
                }
                if(isCompleted(timelogs[i][j], schedules[i])) {
                    count++;
                }
            }
            if(count == 5) answer++;
        }
        
        return answer;
    }
    
    public boolean isCompleted(int timelog, int goal) {
        int[] goalArray = timelogToTime(goal);
        int goalHour = goalArray[0];
        int goalMin = goalArray[1];
        
        int[] timeArray = timelogToTime(timelog);
        int hour = timeArray[0];
        int min = timeArray[1];
                    
        int timeSum = hour * 60 + min;
        int goalSum = goalHour * 60 + goalMin + 10;
        
        if(goalSum >= timeSum) {
            return true;
        }
        return false;
    }
    
    public int[] timelogToTime(int timelog) {
        int hour = timelog / 100;
        int min = timelog % 100;
        
        return new int[] {hour, min};
    }
}