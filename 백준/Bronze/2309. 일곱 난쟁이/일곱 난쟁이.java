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

    // 오답노트) 맞는 조합을 찾아도 프로세스가 종료되지 않고 계속 찾는 문제가 있었음.
    // 그래서 답이 여러 개 출력되게됨.
    // flag를 도입해서 컨트롤하면 조건을 잘못 줬을 때, r이나 depth가 계속 증가 혹은 감소하여 index out of bound 발생할 수도 있음.

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
