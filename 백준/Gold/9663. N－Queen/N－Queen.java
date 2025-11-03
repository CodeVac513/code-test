

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 9663번
    // 백트래킹
    // 어떤 상태를 복원해야 하는가?
    // 한 행에서 열을 한 칸 씩 옮기며 모든 경우의 수를 탐색한다.
    // 그 다음 지금까지 놓인 queen들과 충돌하는 경우가 있는지 isPossible 따위를 호출해서 확인한다.
    // 통과한다면 재귀함수의 입력인 row를 하나 더해서 호출한다.
    // isPossible은 같은 열에 있는지만 검색한다. (row는 이미 하나씩 놓으면서 검사 중임.)

    static int N;
    static int[] queen;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        queen = new int[N];
        answer = 0;

        backtrack(0);

        bw.write(String.valueOf(answer));

        bw.flush();
        bw.close();
        br.close();
    }

    static void backtrack(int row) {

        if (row == N) {
            answer++;
            return;
        }

        for (int col = 0; col < N; col++) {
            queen[row] = col;
            if (isPossible(row)) {
                backtrack(row + 1);
            }
        }
    }

    static boolean isPossible(int row) {
        for (int i = 0; i < row; i++) {
            if (queen[i] == queen[row]) {
                return false;
            }
            if (Math.abs(row - i) == Math.abs(queen[row] - queen[i])) {
                // 기울기가 1인지 검사해서 대각선에 위치한 퀸이 있는지 확인
                return false;
            }
        }
        return true;
    }
}
