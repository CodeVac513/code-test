import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 2309번

    static int N;
    static int[] height;
    static int[] answer;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = 9;
        height = new int[N];
        visited = new boolean[N];
        answer = new int[7];
        for (int i = 0; i < N; i++) {
            height[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(height);

        combination(0, 7, 0);
        bw.flush();
        bw.close();
        br.close();
    }

    static void combination(int start, int r, int depth) {
        if (r == 0) {
            if(isCorrectArray()) {
                printAnswer();
            }
            return;
        }

        for(int i = start; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                answer[depth] = height[i];
                combination(i + 1, r - 1, depth + 1);
                visited[i] = false;
            }
        }
    }

    static boolean isCorrectArray() {
        int sum = 0;
        for (int i = 0; i < 7; i++) {
            sum += answer[i];
        }

        if (sum == 100) return true;
        return false;
    }

    static void printAnswer() {
        Arrays.sort(answer);
        for (int i = 0; i < 7; i++) {
            System.out.println(answer[i]);
        }
        System.exit(0);
    }
}


