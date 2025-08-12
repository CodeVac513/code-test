import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 32748번
    // set을 활용하는 문제
    // 문자열을 잘라서 만들 수 있는 모든 경우의 수를 set에 삽입하면 set의 특성으로 중첩되는 데이터는 삭제할 것이다.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();
        Set<String> subStrings = new HashSet<>();
        for (int i = 0; i < target.length(); i++) {
            for (int j = i + 1; j < target.length() + 1; j++) {
                subStrings.add(target.substring(i, j));
            }
        }
        bw.write(subStrings.size() + "\n");

        br.close();
        bw.flush();
        bw.close();
    }
}