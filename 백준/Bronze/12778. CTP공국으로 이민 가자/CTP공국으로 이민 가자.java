import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 12778번
    // 문자열, 구현
    // CTP 공국식 글자가 주어졌을 때 알파벳 쓰기, 알파벳이 주어졌을 때 CTP 공국식 글자 쓰기 2가지 형태로 로직이 나누어짐.
    // 문제의 개수와 문제 종류를 입력받고 그 입력에 맞게 출력하는 문제
    // 그냥 문제에 주어진 규칙대로 구현만 잘 하면 됨.

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            String quizType = st.nextToken();
            String[] input = br.readLine().split(" ");
            String ans = solve(M, quizType, input);
            bw.write(ans + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static String solve(int M, String quizType, String[] input) {
        StringBuilder sb = new StringBuilder();
        if (quizType.equals("C")) {
            for (int i = 0; i < M; i++) {
                int result = alphabetToInteger(input[i]);
                sb.append(result).append(" ");
            }
        } else {
            for (int i = 0; i < M; i++) {
                String result = integerToAlphabet(Integer.parseInt(input[i]));
                sb.append(result).append(" ");
            }
        }
        return sb.toString();
    }

    public static int alphabetToInteger(String input) {
        return input.charAt(0) - 'A' + 1;
    }

    public static String integerToAlphabet(int input) {
        return String.valueOf((char) ('A' + input - 1));
    }
}
