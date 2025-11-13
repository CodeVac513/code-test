import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 9663
    // 백트래킹
    // 유명한 백트래킹 문제
    // 행마다 특정 열에 queen을 배치함
    // 다음 행으로 넘어갈 때마다 배치될 수 있는 queen 인지 검사함.
    // 대각선은 기울기의 ABS가 1인지만 확인하면 됨.
    // row에서 하나의 col을 골라야 함.
    // queensCol[row] = col → "row번째 행에 col번 열에 퀸을 놓는다"
    // 그러면 k번째 row에서 l번째 col에 위치한다고 가정하면 |row - k| == |col - l|이 되면 기울기가 1인 거고 위치할 수 없음.

    static int[] queensCol;
    static int N;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        answer = 0;
        queensCol = new int[N + 1];
        backtracking(0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static void backtracking(int row) {
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


