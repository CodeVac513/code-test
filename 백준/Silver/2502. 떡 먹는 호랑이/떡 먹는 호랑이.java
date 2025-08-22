import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 2502번
    // 수열, 수학
    // 규칙을 찾는 문제
    // Day[today - 1] + Day[today - 2]의 값을 계속 작게 나눠서 비교해보면, Day 2와 Day 1 각각의 피보나치 수열을 발견할 수 있다.
    // ex ) day 3 = day 2 + day 1, day 4 = day 2 * 2 + day 1, day 5 = day2 * 3 + day 1 * 2
    // day 1은 1, 1, 2, 3, 5, ...
    // day 2는 1, 2, 3, 5, 8, ...
    // D까지의 모든 피보나치 수열을 저장해놓고 직선 위의 해를 구하면 됨. x(day 2의 떡 갯수 * D날에 더해진 횟수) + y(day 1의 떡 갯수 * D날에 더해진 횟수) = K

    static int[] ricecakeByDay;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] day1fibonacci = new int[D + 1];
        int[] day2fibonacci = new int[D + 1];
        day1fibonacci[3] = 1;
        day2fibonacci[3] = 1;
        if (D > 3) {
            day1fibonacci[4] = 1;
            day2fibonacci[4] = 2;
        }

        for (int i = 5; i <= D; i++) {
            day1fibonacci[i] = day1fibonacci[i - 1] + day1fibonacci[i - 2];
            day2fibonacci[i] = day2fibonacci[i - 1] + day2fibonacci[i - 2];
        }

        int result = 0;
        int x = 0, y = 0;
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= K; j++) {
                int currentRiceCake = day1fibonacci[D] * i + day2fibonacci[D] * j;
                if (currentRiceCake == K) {
                    result = currentRiceCake;
                    x = i;
                    y = j;
                    break;
                } else if (currentRiceCake > K) {
                    break;
                }
            }
            if (result == K) {
                break;
            }
        }

        bw.write(String.valueOf(x) + "\n" + String.valueOf(y));

        br.close();
        bw.flush();
        bw.close();
    }

}