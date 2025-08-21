import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 20944번
    // 문자열, 구현문제
    // 펠린드롬이면서 수미상관인 문자열을 출력한다.
    // 가장 간단한 건 a를 N번 출력하는 것

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append("a");
        }

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

}