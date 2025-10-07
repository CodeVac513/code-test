import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 2110번
    // 파라메트릭 서치
    // 두 공유기 사이의 거리를 최대로 하는 최적화 문제를 결정 문제로 끌어내려야 한다.
    // 숫자 N을 주면서, 문제 조건을 만족하는지 바이너리 서치를 통해 검사한다.
    // 그러면 그 간격은 어떻게 되어야 할까?
    // 일정 거리에 있는 집이 몇 개 인지 세서, 그 집의 개수가 공유기의 개수보다 같거나 작은지를 판별하면 될 듯?
    // left는 집이 사이 간격이 가장 짧을 수 있는 경우인 1, right는 1000000000으로 설정하면 됨.
    // => 위의 left와 right는 반지름을 기준으로 하는 것인가? 아니면 지름을 기준으로 하는 것인가?
    // 집을 중심으로 찾아야 하나? 아니면 간격 내에 집이 있기만 하면 되도록 만들어야 하나?
    // 내가 지금 의문이 드는 부분) 집 = 공유기이므로, 설치할 집을 골라야 함.
    // 이 부분을 어떻게 해결하지?

    // 다시 정리해보자
    // 최소거리가 X 이상 되도록 C개의 공유기를 설치할 수 있는가?
    // 그러면 공유기를 어떻게 설치할 것인가?
    // => 이 문제는 공유기가 어디까지를 커버할 수 있는지에 대한 문제가 아님.
    // 그냥 두 집 사이의 공유기를 최대한 떨어뜨려 놓는 게 목표임.

    // 오답 노트)
    // isPossible에서 첫 번째 집에 공유기를 설치해야 하는데, count가 0부터 시작함.
    // C개의 공유기를 설치해야 하므로, 공유기가 적으면 실패 / 많거나 같으면 성공이다.
    // 성공했을 때는 간격을 늘리고, 실패했을 때는 간격을 줄여야 한다.
    // 최소 거리가 X 이상이므로 houseInterval >= Interval의 조건을 만족해야 count가 올라간다.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] houses = new int[N];
        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        bw.write(String.valueOf(binarySearch(houses, C)));

        br.close();
        bw.flush();
        bw.close();
    }

    public static long binarySearch(int[] houses, int target) {
        long left = 1;
        long right = 1000000000;

        long answer = 0;
        while (left <= right) {
            long interval = left + (right - left) / 2;

            if (isPossible(houses, interval, target)) {
                answer = interval;
                left = interval + 1;
            } else {
                right = interval - 1;
            }
        }

        return answer;
    }

    public static boolean isPossible(int[] houses, long interval, int target) {
        int count = 1;
        int prevHouse = houses[0];
        for (int i = 1; i < houses.length; i++) {
            int currentHouse = houses[i];
            int houseInterval = currentHouse - prevHouse;
            if (houseInterval >= interval) {
                prevHouse = currentHouse;
                count++;
            }
        }
        return count >= target ? true : false;

    }

}
