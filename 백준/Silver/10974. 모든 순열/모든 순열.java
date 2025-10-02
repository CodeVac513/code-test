import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 10974번
    // 순열

    static int limitDepth;
    static boolean[] visited;
    static int[] ans;
    static int[] initArray;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        limitDepth = Integer.parseInt(br.readLine());
        initArray = new int[limitDepth];
        for (int i = 0; i < limitDepth; i++) {
            initArray[i] = i + 1;
        }
        visited = new boolean[limitDepth];
        ans = new int[limitDepth];
        permutation(0);
        br.close();
        bw.flush();
        bw.close();
    }

    static void permutation(int currentDepth) {

        if (currentDepth == limitDepth) {
            printAnswer();
            return;
        }

        for (int i = 0; i < limitDepth; i++) {
            if (!visited[i]) {
                visited[i] = true;
                ans[currentDepth] = initArray[i];
                permutation(currentDepth + 1);
                visited[i] = false;
            }
        }
    }

    static void printAnswer() {
        for(int i = 0; i < limitDepth; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }

}
