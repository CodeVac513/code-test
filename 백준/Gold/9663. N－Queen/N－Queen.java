import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 9663
    // 백트래킹
    // n-queen

    static int[] queensCol;
    static int answer = 0;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        queensCol = new int[N];
        backtracking(0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void backtracking(int row) {
        if (row == N) {
            answer++;
            return;
        }

        for (int col = 0; col < N; col++) {
            queensCol[row] = col;
            boolean canLocated = true;

            for (int i = 0; i < row; i++) {
                if (queensCol[i] == col) {
                    canLocated = false;
                    break;
                }

                if (Math.abs(row - i) == Math.abs(queensCol[row] - queensCol[i])) {
                    canLocated = false;
                    break;

                }
            }

            if (!canLocated)
                continue;

            backtracking(row + 1);
        }
    }
}