import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1759번
    // 조합 문제, 백트래킹
    // 백트래킹을 통해서 적절한 조합을 찾아야 함.
    // 알파벳의 순서 구분이 없으므로 순열이 아니라 조합임.
    // 오답 노트) 자음과 모음의 개수에 대한 제한을 고려 안 했음.

    static String[] initArray;
    static boolean[] visited;
    static String[] answer;
    static int L;
    static int N;
    static ArrayList<String> answerList;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        initArray = br.readLine().split(" ");
        Arrays.sort(initArray);

        visited = new boolean[N];
        answer = new String[L];
        answerList = new ArrayList<>();
        combination(0, L, 0);

        for (String s : answerList) {
            bw.write(s + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void combination(int startIndex, int r, int depth) {
        if (r == 0) {
            if (isValidPassword()) {
                insertAnswer();
            }
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

    public static boolean isValidPassword() {
        int countVowels = 0;
        int countConsonants = 0;

        for (int i = 0; i < L; i++) {
            switch (answer[i]) {
                case "a":
                    countVowels++;
                    break;
                case "e":
                    countVowels++;
                    break;
                case "i":
                    countVowels++;
                    break;
                case "o":
                    countVowels++;
                    break;
                case "u":
                    countVowels++;
                    break;
                default:
                    countConsonants++;
            }
        }

        if (countVowels >= 1 && countConsonants >= 2) {
            return true;
        }
        return false;
    }

    public static void insertAnswer() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < answer.length; i++) {
            sb.append(answer[i]);
        }
        answerList.add(sb.toString());
    }
}
