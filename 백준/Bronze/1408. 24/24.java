import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 1408번
    // 구현 문제
    // 시간 계산을 구현
    // 시간이 부족하면 시나 분에서 1을 가져오고 60을 더해주는 식으로...
    // 만약 현재 시간이 임무를 시작한 시간보다 작으면 시작 시간 - 현재 시간으로 구할 수 있다.
    // 그 반대라면 24시간 - (현재 시간 - 시작 시간)으로 구할 수 있다.
    // 오답 노트)
    // 위 방법대로 하면 if 조건 분기 노가다가 필요하다.
    // 더 간단한 방법으로는 모든 시간을 초로 환산한 뒤에 계산하는 것이다.
    // String.format을 사용하는데, %02d의 의미는 다음과 같다.
    // % : 포맷 지정자 시작
    // 0 : 패딩 문자, 빈 자리를 이 문자로 채움
    // 2 : 최소 너비, 최소 길이가 2라는 의미
    // d : decimal, 정수 타입
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(":");
        Integer[] currentTime = new Integer[temp.length];
        for (int i = 0; i < temp.length; i++) {
            currentTime[i] = Integer.parseInt(temp[i]);
        }
        temp = br.readLine().split(":");
        Integer[] startTime = new Integer[temp.length];
        for (int i = 0; i < temp.length; i++) {
            startTime[i] = Integer.parseInt(temp[i]);
        }
        int currentTimeToSecond = currentTime[0] * 3600 + currentTime[1] * 60 + currentTime[2];
        int startTimeToSecond = startTime[0] * 3600 + startTime[1] * 60 + startTime[2];

        int twentyfourHours = 3600 * 24;

        int remainTime = 0;
        if (currentTimeToSecond > startTimeToSecond) {
            remainTime = twentyfourHours - (currentTimeToSecond - startTimeToSecond);
        } else {
            remainTime = startTimeToSecond - currentTimeToSecond;
        }

        Integer[] ans = new Integer[3];
        ans[0] = remainTime / 3600;
        ans[1] = remainTime % 3600 / 60;
        ans[2] = remainTime % 3600 % 60;

        bw.write(String.format("%02d:%02d:%02d", ans[0], ans[1], ans[2]));

        br.close();
        bw.flush();
        bw.close();
    }
}