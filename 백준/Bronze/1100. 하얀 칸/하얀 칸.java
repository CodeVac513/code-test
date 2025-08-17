import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    // 1100번 문제
    // 구현?
    // 0,0은 흰색이며 8*8의 체스판에서 하얀 칸 위의 말의 개수를 구해야 한다.
    // 흰색은 좌표의 x,y 값이 모두 짝수이거나 모두 홀수이다.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[][] chess = new String[8][8];

        for (int i = 0; i < 8; i++) {
            String[] temp = br.readLine().split("");
            chess[i] = temp;
        }

        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < 8; j += 2) {
                    if (chess[i][j].equals("F")) {
                        count++;
                    }
                }
            } else {
                for (int j = 1; j < 8; j += 2) {
                    if (chess[i][j].equals("F")) {
                        count++;
                    }
                }
            }
        }

        bw.write(Integer.toString(count));

        bw.flush();
        bw.close();
        br.close();
    }
}
