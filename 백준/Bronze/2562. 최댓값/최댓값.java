import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 2562번
    // 구현
    // 문제에서 주어진대로 가장 큰 숫자와 그 index를 저장하면서 N번 비교하면 됨

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 1; i <= 9; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > ans) {
                ans = num;
                index = i;
            }
        }

        bw.write(String.valueOf(ans) + "\n" + String.valueOf(index));
        br.close();
        bw.flush();
        bw.close();
    }

}