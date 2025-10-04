import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1182번
    //
    // N개의 정수 중 크기가 양수인 부분 수열 <- 1개 이상의 원소를 골라서 더해야 함.
    // 결국 조합의 문제임
    // 어떤 조합을 통해서 합이 S가 되는 것의 개수를 구하면 됨.
    static int count = 0;
    static int[] initArray;
    static boolean[] visited;
    static int[] answer;
    static int N;
    static int S;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        initArray = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();


        for (int i = 1; i <= N; i++) {
            answer = new int[i];
            visited = new boolean[N];
            combination(0, i, 0);
        }

        bw.write(String.valueOf(count));

        br.close();
        bw.flush();
        bw.close();
    }

    public static void combination(int startIndex, int r, int depth) {
        if (r == 0) {
            if (getAnswer() == S) {
                count++;
            }
            return;
        }

        for (int i = startIndex; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                answer[depth] = initArray[i];
                combination(i + 1, r - 1, depth + 1);
                visited[i] = false;
            }
        }
    }

    public static int getAnswer() {
        int sum = 0;
        for (int i = 0; i < answer.length; i++) {
            sum += answer[i];
        }
        return sum;
    }
}
