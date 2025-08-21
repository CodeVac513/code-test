import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 4673번
    // 브루트포스
    // 1부터 시작해서 10000보다 작은 모든 d(n)을 구해서 배열로 관리한다.
    static Integer[] countVisited = new Integer[10001];

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Arrays.fill(countVisited, 0);

        for (int i = 1; i <= 10000; i++) {
            recursive(i);
        }

        for (int i = 1; i <= 10000; i++) {
            if (countVisited[i] == 1) {
                bw.write(i + "\n");
            }
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static private void recursive(int current) {
        if (current >= 10001) {
            return;
        }
        countVisited[current]++;
        recursive(calculdate(current));
    }

    static private Integer calculdate(int i) {
        return i + (i / 1000) + (i % 1000) / 100 + (i % 100) / 10 + i % 10;
    }

}