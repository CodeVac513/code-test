import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 15649번
    // 순열
    // N개의 숫자 중 M개를 고르는 전형적인 순열 문제

    static int limitDepth;
    static boolean[] visited;
    static int[] ans;
    static int[] initArray;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[limitDepth];

        limitDepth = Integer.parseInt(st.nextToken());
        ans = new int[limitDepth];
        initArray = new int[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            initArray[i] = i + 1;
        }
        permutation(0);
        br.close();
        bw.flush();
        bw.close();
    }

    static void permutation(int currentDepth) {
        if (currentDepth == limitDepth) {
            printAnsArray();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                ans[currentDepth] = initArray[i];
                permutation(currentDepth + 1);
                visited[i] = false;
            }
        }
    }

    static void printAnsArray() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < limitDepth; i++) {
            sb.append(ans[i] + " ");
        }
        System.out.println(sb.toString());
    }

}
