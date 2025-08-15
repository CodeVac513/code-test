import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 32978번 문제
    // 구현 문제
    // 문자열을 비교한다.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] ingredients = br.readLine().split(" ");

        String[] used = br.readLine().split(" ");

        HashMap<String, Boolean> usedList = new HashMap<>();

        for (String s : used) {
            usedList.put(s, true);
        }
        StringBuilder sb = new StringBuilder();
        for (String s : ingredients) {
            if (!usedList.containsKey(s)) {
                sb.append(s);
            }
        }

        bw.write(sb.toString() + "\n");

        br.close();
        bw.flush();
        bw.close();
    }
}