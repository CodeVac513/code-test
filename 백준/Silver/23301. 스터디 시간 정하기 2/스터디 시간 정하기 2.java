import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    // 23301 문제
    // 투포인터 문제
    // 구간을 배열로 설정하고
    // 스터디원들의 참여 시간을 1시간 단위로 잘라서 가능한 인원수를 구한다.
    // 시작 포인터와 끝 포인터를 1칸씩 옮기며 최대 값을 업데이트한다.
    // (포인터 사이의 간격은 스터디 시간)
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        Integer[] timeTable = new Integer[1001];
        Arrays.fill(timeTable, 0);

        for (int person = 0; person < N; person++) {
            int iter = Integer.parseInt(br.readLine());
            for (int i = 0; i < iter; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken()) + 1;
                int end = Integer.parseInt(st.nextToken());

                for (int time = start; time <= end; time++) {
                    timeTable[time]++;
                }
            }
        }

        int start = 0;
        int end = start + T;

        int resultStart = start;
        int resultEnd = end;
        int result = 0;

        while (end <= 1001) {
            int current = 0;
            for (int index = start; index < end; index++) {
                current += timeTable[index];
            }
            if (current > result) {
                result = current;
                resultStart = start;
                resultEnd = end;
            }
            start++;
            end++;
        }

        bw.write((resultStart - 1) + " " + (resultEnd - 1) + "\n");

        bw.flush();
        bw.close();
        br.close();
    }
}
