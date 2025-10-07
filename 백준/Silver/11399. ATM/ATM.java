import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 11399번
    // 그리디
    // 사람은 1번부터 N번까지 번호
    // i번째 사람이 돈을 인출하는데 걸리는 시간은 P(i)분.
    // 그리디는 논리의 중요성이 필요함. 그리고 이것이 최적의 해가 맞는지 확인해야 함.
    // 이 문제의 경우, 먼저 줄을 선 사람 기준으로 중복되어 시간 계산이 이루어짐.
    // 그렇다면 당연히 시간이 짧은 사람을 먼저 세우는 것이 항상 최적의 해를 뱉을 것임.
    // 코테도 이 정도로 생각할 수 있는 문제가 나오면 얼마나 좋을까.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] P = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(P);

        bw.write(String.valueOf(greedy(N, P)));

        br.close();
        bw.flush();
        bw.close();
    }

    public static int greedy(int N, int[] P) {
        int sum = 0;
        int totalTime = 0;
        for (int i = 0; i < N; i++) {
            totalTime += P[i] + sum;
            sum += P[i];
        }

        return totalTime;
    }
}
