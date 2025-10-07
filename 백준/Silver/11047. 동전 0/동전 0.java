import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 11047번
    // 그리디
    // 동전을 N개 가지고 있음. K라는 값어치만큼 동전을 고를건데, 필요한 동전의 개수 최솟값을 구해야 함.
    // N개의 줄에 동전의 가치 A가 오름차순으로 주어짐.
    // 이 때, 바로 다음 동전은 바로 이전 동전의 배수임.
    // -> 첫 번째 동전은 1 이상 백만이하고, 그 다음부터는 그 동전의 배수임.
    // 여기서 2배인지, 5배인지, 10배인지 등 배수의 크기는 고정되지 않음. 다만 X배 하면 다음 동전을 만들 수 있음.
    // 다음 동전이 이전 동전의 배수이므로, 최대한 큰 값을 가지는 동전을 많이 사용하는 것이 항상 최선의 해를 만들 수 있음.
    // (그 이유는 다음 동전이 배수라는 말은 역으로 작은 동전으로 해당 값을 만들 수 있다는 의미이기 때문)

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Integer[] coinValues = new Integer[N];
        Arrays.fill(coinValues, 0);
        for (int i = 0; i < N; i++) {
            coinValues[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coinValues, Collections.reverseOrder());
        bw.write(String.valueOf(greedy(coinValues, K)));

        br.close();
        bw.flush();
        bw.close();
    }

    public static int greedy(Integer[] coinValues, int goal) {
        int count = 0;
        for (Integer coinValue : coinValues) {
            if (coinValue > goal) {
                continue;
            }

            int coins = goal / coinValue;
            goal -= coins * coinValue;
            count += coins;
        }

        return count;
    }
}
