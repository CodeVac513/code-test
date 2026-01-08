import java.util.*;

class Solution {
    int videoLength;
    int current;
    int opStart;
    int opEnd;
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        videoLength = timeToSeconds(video_len);
        current = timeToSeconds(pos);
        opStart = timeToSeconds(op_start);
        opEnd = timeToSeconds(op_end);
        if(current >= opStart && current < opEnd) {
            current = opEnd;
        }
        for(String command : commands) {
            if(command.equals("prev")) {
                current = goToPrev(current);
            } else if(command.equals("next")) {
                current = goToNext(current);
            }
            if(current >= opStart && current < opEnd) {
                current = opEnd;
            }
        }
        
        return secondsToTime(current);
    }
    public int goToPrev(int time) {
        if(time <= 10) {
            return 0;
        }
        
        return time - 10;
    }
    
    public int goToNext(int time) {
        if((videoLength - time) <= 10) {
            return videoLength;
        }
        
        return time + 10;
    }
    
    public String secondsToTime(int seconds) {
        int minutes = seconds / 60;
        seconds = seconds % 60;
        StringBuilder sb = new StringBuilder();
        return sb.append(String.format("%02d", minutes)).append(":").append(String.format("%02d", seconds)).toString();
    }
    
    public int timeToSeconds(String time) {
        String[] timeArray = time.split(":");
        return Integer.parseInt(timeArray[0]) * 60 + Integer.parseInt(timeArray[1]);
    }
}


// 요구사항
// 1. 10초 전 이동, 현재 시각이 10초 미만이면 시작 위치 (0분 0초)로 이동
// 2. 10초 후 이동, 남은 시각이 10초 미만이면 영상의 마지막으로 (영상의 길이)
// 3. 오프닝 건너뛰기, 현재 재생 위치가 오프닝 구간이면 오프닝이 끝나는 위치로(op_end)

// mm:ss의 형태이므로, 이해하기 쉽게 초로 변환하고 다시 되돌리는 헬퍼 메서드가 있으면 좋을듯

