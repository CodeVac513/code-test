import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 6603번
    // 조합
    // K개의 숫자 중 6개를 골라내는 문제
    static int[] s;
    static int k;
    static StringBuilder sb;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            sb = new StringBuilder();
            answer = new int[6];
            if (k == 0)
                break;
            s = new int[k];
            for (int i = 0; i < k; i++) {
                s[i] = Integer.parseInt(st.nextToken());
            }

            combination(0, 0);
            bw.write(sb.toString());
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void combination(int startIndex, int depth) {
        if (depth == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = startIndex; i < k; i++) {

            answer[depth] = s[i];
            combination(i + 1, depth + 1);
        }
    }
}
