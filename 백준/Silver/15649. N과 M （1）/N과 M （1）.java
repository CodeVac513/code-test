

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 15649번
    // 순열
    // 순서가 있음. N개의 숫자 중 M개를 뽑아야 함.

    static int N;
    static int M;
    static int[] nums;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];
        visited = new boolean[N];
        for (int i = 1; i <= N; i++) {
            nums[i - 1] = i;
        }

        permutation(0, "");

        bw.flush();
        bw.close();
        br.close();
    }

    static void permutation(int depth, String ans) {
        if (depth == M) {
            System.out.println(ans.trim());
        }

        for (int i = 0; i < N; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            permutation(depth + 1, ans + " " + nums[i]);
            visited[i] = false;
        }
    }

}
