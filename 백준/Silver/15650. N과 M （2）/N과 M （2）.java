import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 15650번
    // 조합
    // N개 중 M개를 골라서 조합을 만들어야 함.
    // 그 중에서 오름차순이어야하므로, 순열처럼 모든 조합을 구하는 게 아님. 중복을 허용하지 않는다는 것을 알 수 있음.

    static int N;
    static int M;
    static boolean[] visited;
    static int[] initArray;
    static int[] answer;
    static ArrayList<String> answerList;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        initArray = new int[N];
        for (int i = 0; i < N; i++) {
            initArray[i] = i + 1;
        }
        answer = new int[M];
        answerList = new ArrayList<>();

        combination(0, M, 0);

        for (int i = 0; i < answerList.size(); i++) {
            bw.write(answerList.get(i));
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static void combination(int startIndex, int r, int depth) {

        if (r == 0) {
            answerList.add(integerArrayToString(answer));
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

    static String integerArrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]).append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }
}
