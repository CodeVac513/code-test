import java.io.*;

public class Main {
    // 10993번
    // 재귀
    // 층은 2^(N) - 1
    // 별이 가장 많이 찍히는 변의 길이는 2^(N + 1) - 3임을 확인
    // 솔직히 이해 못해서 C++ 해설지를 참고했다.
    //
    static char[][] board = new char[2048][2048];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 배열을 공백으로 초기화
        for (int i = 0; i < 2048; i++) {
            for (int j = 0; j < 2048; j++) {
                board[i][j] = ' ';
            }
        }

        // N=1인 경우 특별 처리
        if (N == 1) {
            bw.write("*\n");
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        int width = (int) Math.pow(2, N + 1) - 3;
        int height = (int) Math.pow(2, N) - 1;

        recursion(0, 0, N);

        // 각 라인마다 마지막 별표가 오는 지점까지 출력
        if (N % 2 == 0) { // 짝수: 아래를 향하는 삼각형 ▼
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width - i; j++) {
                    bw.write(board[i][j]);
                }
                bw.write('\n');
            }
        } else { // 홀수: 위를 향하는 삼각형 ▲
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width - height + i + 1; j++) {
                    bw.write(board[i][j]);
                }
                bw.write('\n');
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void recursion(int x, int y, int N) {
        if (N == 1) {
            board[x][y] = '*';
            return;
        }

        int width = (int) Math.pow(2, N + 1) - 3;
        int height = (int) Math.pow(2, N) - 1;

        // depth => 짝수 => ▼ (아래를 향하는 삼각형)
        if (N % 2 == 0) {
            // 삼각형의 가장 윗부분을 만든다
            for (int i = y; i < width + y; i++) {
                board[x][i] = '*';
            }

            // 위에서부터 내려가며 대각선 생성
            for (int i = 1; i < height; i++) {
                board[x + i][y + i] = '*'; // 왼쪽 대각선
                board[x + i][y + width - i - 1] = '*'; // 오른쪽 대각선
            }

            // 내부 삼각형 재귀 호출
            recursion(x + 1, y + (int) Math.pow(2, N - 1), N - 1);
        }
        // depth => 홀수 => ▲ (위를 향하는 삼각형)
        else {
            // 삼각형의 가장 밑부분을 만든다
            for (int i = y; i < width + y; i++) {
                board[x + height - 1][i] = '*';
            }

            // 밑에서 위로 올라가며 대각선 만든다
            for (int i = 0; i < height; i++) {
                board[x + i][y + width / 2 - i] = '*'; // 왼쪽 대각선
                board[x + i][y + width / 2 + i] = '*'; // 오른쪽 대각선
            }

            // 내부 삼각형 재귀 호출
            recursion(x + height / 2, y + (int) Math.pow(2, N - 1), N - 1);
        }
    }
}
