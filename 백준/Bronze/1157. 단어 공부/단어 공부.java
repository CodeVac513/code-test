import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 1157번
    // 문자열 구현 문제
    // 대소문자 구분이 없으므로 입력받은 문자열을 모두 lowerCase나 upperCase로 만드는 방법을 사용하자.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Character, Integer> alphabetCount = new HashMap<>();
        String target = br.readLine().toLowerCase();
        for (int i = 0; i < 26; i++) {
            alphabetCount.put((char) ('a' + i), 0);
        }
        int[] ans = new int[26];
        for (int i = 0; i < target.length(); i++) {
            ans[target.charAt(i) - 'a']++;
        }

        int max = 0;
        char targetChar = '?';

        for (int i = 0; i < 26; i++) {
            if (ans[i] > max) {
                max = ans[i];
                targetChar = (char) ('A' + i);
            } else if (ans[i] == max && max > 0) {
                targetChar = '?';
            }
        }


        bw.write(String.valueOf(targetChar));
        br.close();
        bw.flush();
        bw.close();
    }
}