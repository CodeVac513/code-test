import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 2798번
    // 조합
    // N개의 카드 중 3개를 골라서, 합이 M은 넘지 않는 최대의 가까운 값을 만들어야 한다.

    static int N;
    static int M;
    static boolean[] visited;
    static int[] initArray;
    static int[] answer;
    static int biggest = 0;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        answer = new int[N];
        initArray = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        combination(0, 3, 0);

        bw.write(String.valueOf(biggest));

        br.close();
        bw.flush();
        bw.close();
    }

    static void combination(int startIndex, int r, int depth) {
        if (r == 0) {
            checkAnswer();
            return;
        }

        for (int i = startIndex; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                answer[depth] = initArray[i];
                combination(startIndex + 1, r - 1, depth + 1);
                visited[i] = false;
            }
        }
    }

    static boolean checkAnswer() {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += answer[i];
        }

        if (sum > biggest && sum <= M) {
            biggest = sum;
            return true;
        }

        return false;
    }


}
