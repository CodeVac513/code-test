import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1182
    // 백트래킹
    // N개의 정수, 크기가 양수인 부분 수열에서 S가 되는 경우의 수
    // 오답 노트)
    // 선택과 비선택, 두 가지의 선택지를 트래킹해야 함. 전형적인 조합으로 구하는 것처럼 생각하면 안됨.
    // 부분집합을 구하는 문제임.
    // 오답 노트 2)
    // 공집합을 생각해야 함.

    static int N;
    static int S;
    static int[] nums;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        backtracking(0, 0, 0);
        bw.write(String.valueOf(answer));

        bw.flush();
        bw.close();
        br.close();
    }

    static void backtracking(int sum, int depth, int count) {
        if (depth == N) {
            if (sum == S && count > 0) {
                answer++;
            }
            return;
        }

        backtracking(sum + nums[depth], depth + 1, count + 1);
        backtracking(sum, depth + 1, count);
    }
}
