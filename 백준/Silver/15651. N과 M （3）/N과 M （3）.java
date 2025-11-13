

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 15651번
    // 중복 순열
    // 순열인데, 중복되는 값을 사용해도 됨. << visited 배열이 필요없음.

    // 오답노트)
    // 시간초과가 발생함.
    // String 때문인지 확인하기 위해,answer[] 라는 array를 만들 예정.
    static int N;
    static int M;
    static int[] nums;
    static int[] answer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];
        answer = new int[M];
        for (int i = 1; i <= N; i++) {
            nums[i - 1] = i;
        }

        permutation(0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void permutation(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            answer[depth] = nums[i];
            permutation(depth + 1);
        }
    }

}
