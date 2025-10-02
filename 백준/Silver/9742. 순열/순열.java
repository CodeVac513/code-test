import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 9742번
    // 순열
    // 순열을 구현하기 위해서 DFS와 백트래킹을 사용한다.
    // 오답노트) 메모리 초과 발생. result를 모두 구하지 말고 중간에 나갈 수 있도록 만드는 게 중요할 듯?
    // string을 매번 새로 생성하는 과정에서 메모리 초과가 발생하는 것으로 추측

    static char[] chars;  // String 대신 char 배열 사용
    static int permCount = 0;
    static int n;
    static String[] initArray;
    static boolean[] visited;
    static int limit;
    static String answer;
    static String originalString;


    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputString = "";
        while ((inputString = br.readLine()) != null && !inputString.isEmpty()) {
            StringTokenizer st = new StringTokenizer(inputString);
            originalString = st.nextToken();
            limit = originalString.length();
            initArray = originalString.split("");
            n = Integer.parseInt(st.nextToken());
            visited = new boolean[limit];
            permCount = 0;
            chars = new char[limit];
            answer = null;
            Arrays.fill(visited, false);

            permutation(0);

            if (answer == null) {
                bw.write(originalString + " " + n + " = " + "No permutation");
            } else {
                bw.write(originalString + " " + n + " = " + answer);
            }
            bw.write("\n");
            bw.flush();
        }


        br.close();
        bw.flush();
        bw.close();
    }

    static void permutation(int currentDepth) {
        if (answer != null) return;  // 조기 종료

        if (currentDepth == limit) {
            permCount++;
            if (permCount == n) {
                answer = new String(chars);  // 찾았을 때만 String 생성
            }
            return;
        }

        for (int i = 0; i < limit; i++) {
            if (!visited[i]) {
                visited[i] = true;
                chars[currentDepth] = originalString.charAt(i);  // 배열에 저장
                permutation(currentDepth + 1);
                visited[i] = false;
            }
        }
    }

}
