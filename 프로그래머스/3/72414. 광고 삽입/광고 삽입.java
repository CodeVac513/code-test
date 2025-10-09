import java.util.*;
import java.lang.*;

class Solution {
    //  시간을 쪼개서 초로 생각하기
    //    - HH:MM:SS-HH:MM:SS형태로 들어오고 왼쪽은 시작 시간, 오른쪽은 종료 시간을 의미
    // 1. log의 시작 시간 별로 정렬한다.
    // 2. 광고를 log의 시작 시간에 맞춰서 쭉 실행한다.
    // 3. 광고를 실행하면서 어떤 time(초)에 몇 명이 시청했는지 누적시킨다.
    //     이 배열은 전체 길이에 대한 배열일 듯?
    // 4. 다음 log의 시작 시간에 맞춰서 광고를 실행한다.
    //     전체 배열에 대해 값을 누적시키면서 계속 진행한다.
    // 5. 위 과정을 마지막 log까지 반복한다.
    // 6. 가장 큰 count이 있는 시작시간을 찾는다.
    // 위 알고리즘은 틀렸다. 시간 복잡도와 논리에서도 문제가 있다.
    // 변화가 일어나는 지점만 기록해서, 누적합을 효율적으로 계산해야 한다.
    // 차분 배열을 활용해서 변화가 일어나는 지점만 기록해 사용하면 좋다.
    // 슬라이딩 윈도우를 활용해서 누적된 시청자 배열을 돌면서 가장 큰 구간을 찾아야 한다.
    // 즉, 이 문제는 3가지 기법을 사용한다. -> 차분 배열, 누적합, 슬라이딩 윈도우
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        int playTime = timeToSeconds(play_time);
        int advTime = timeToSeconds(adv_time);
        if(playTime == advTime) {
            return secondsToString(0);
        }
        int[][] logsToSeconds = logToSecond(logs);
        int[] allTime = new int[playTime + 1];
        
        Arrays.sort(logsToSeconds, (a, b) -> {
            return  a[0] - b[0];
        });
        
        for(int i = 0 ; i < logs.length ; i++) {
            int startIndex = logsToSeconds[i][0];
            int endIndex = logsToSeconds[i][1];
            allTime[startIndex] += 1;
            allTime[endIndex] -= 1;
        }
        
        int[] prefixSum = new int[playTime + 1];
        
        
        for(int i = 1 ; i <= playTime ; i++) {
            prefixSum[i] = prefixSum[i - 1] + allTime[i];
        }
        
        long sum = 0;
        for(int i = 1; i <= advTime; i++) {
            sum += prefixSum[i];
        }       
        
        long max = sum;
        int startTime = 0;
        for(int i = advTime + 1 ; i <= playTime ; i++) {
            sum -= prefixSum[i - advTime];
            sum += prefixSum[i];
            if(sum > max) {
                max = sum;
                startTime = i - advTime + 1;
            }
        }
        
        return secondsToString(startTime);
    }
    
    public int[][] logToSecond(String[] logs) {
        int[][] result = new int[logs.length][2];
        int index = 0;
        for(String log : logs) {
            String[] tokens = log.split("-");
            result[index][0] = timeToSeconds(tokens[0]);
            result[index][1] = timeToSeconds(tokens[1]);
            index++;
        }
        return result;
    }
    
    public int timeToSeconds(String time) {
        String[] tokens = time.split(":");
        String hourToken = tokens[0];
        String minuteToken = tokens[1];
        String secondToken = tokens[2];
        return Integer.parseInt(hourToken) * 3600 + Integer.parseInt(minuteToken) * 60 + Integer.parseInt(secondToken);
    }
    
    public String secondsToString(int seconds) {
        StringBuilder sb = new StringBuilder();
        int hours = seconds / 3600;
        seconds %= 3600;
        int minutes = seconds / 60;
        seconds %= 60;
        sb.append(String.format("%02d",hours)).append(":").append(String.format("%02d",minutes)).append(":").append(String.format("%02d",seconds));
        return sb.toString();
    }
}