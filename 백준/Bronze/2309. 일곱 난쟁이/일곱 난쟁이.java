import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 2309번
    // 조합
    // 조합이란, n개의 대상에서 r개를 순서 없이 뽑는 경우를 의미한다.
    // 조합에서 신경 쓸 건
    // 1. r을 활용하는 것
    // 2. initArray의 시작하는 부분이 순열처럼 0이 아니라 start Index로 따로 관리되어야 한다는 것.
    // -> 그 이유는 앞서 검색했던 것을 순열처럼 돌아가서 검색하지 않기 때문. 순서를 고려하지 않기 때문에.

    static int[] initArray;
    static boolean[] visited;
    static int R;
    static int N;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = 9;
        R = 7;
        initArray = new int[N];
        visited = new boolean[N];
        answer = new int[R];
        for (int i = 0; i < N; i++) {
            initArray[i] = Integer.parseInt(br.readLine());
        }

        combination(0, 7, 0);

        br.close();
        bw.flush();
        bw.close();
    }

    static void combination(int startIndex, int r, int depth) {
        if (r == 0) {
            if (isCorrectArray()) {
                printAnswer();
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

    static boolean isCorrectArray() {
        int sum = 0;
        for (int i = 0; i < R; i++) {
            sum += answer[i];
        }

        if (sum == 100) return true;
        return false;
    }

    static void printAnswer() {
        Arrays.sort(answer);
        for (int i = 0; i < R; i++) {
            System.out.println(answer[i]);
        }
        System.exit(0);
    }
}
