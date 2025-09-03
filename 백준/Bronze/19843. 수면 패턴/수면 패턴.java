import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 19843번
    // 구현
    // 24시간으로 이루어진 하루를 계산해서 필요한 총 수면 시간에서 빼는 것이 중요함.
    // 날짜의 변환을 어떻게 생각할 지가 중요함.
    // 월요일을 0, 화요일을 24, 수요일을 48, ... 금요일은 96 이런 식으로 계산하면 편할지도? => 그 이유는 잠을 아예 안 잘 수도 있고 하루종일 잘 수 없다는 말도 없기 때문에 잠만 이틀을 내리자는 등의 케이스가 있을 수 있음. 그렇게 되면 요일 별 계산이 복잡해짐. (if문 떡칠)

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int sum = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String startDay = st.nextToken();
            int startHour = Integer.parseInt(st.nextToken());
            String endDay = st.nextToken();
            int endHour = Integer.parseInt(st.nextToken());

            int calculatedStartTime = calculateTime(startDay, startHour);
            int calculatedEndTime = calculateTime(endDay, endHour);

            sum += calculatedEndTime - calculatedStartTime;
        }
        int ans = T - sum;
        if (ans > 48) {
            bw.write(String.valueOf(-1));
        } else if (ans <= 0) {
            bw.write(String.valueOf(0));
        } else {
            bw.write(String.valueOf(ans));
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static int calculateTime(String day, int currentHour) {
        int time = 0;
        switch (day) {
            case "Mon":
                time += 0 + currentHour;
                break;
            case "Tue":
                time += 24 + currentHour;
                break;
            case "Wed":
                time += 48 + currentHour;
                break;
            case "Thu":
                time += 72 + currentHour;
                break;
            case "Fri":
                time += 96 + currentHour;
                break;
        }

        return time;
    }

}