import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 15652번
    // 중복 조합

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

        combination(0, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void combination(int startIndex, int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = startIndex; i < N; i++) {
            answer[depth] = nums[i];
            combination(i, depth + 1);
        }
    }

}
