import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1531번
    // 구현
    // N개의 종이가 있으며 M개를 초과하여 겹쳐야 그림이 가려진다.
    // 모든 좌표는 100이하의 자연수이므로 1~100의 값을 가진다.
    // 1~100의 인덱스를 가지는 이차원 배열을 M으로 초기화하고, 종이의 좌표로 해당 그림의 값을 -1한다.
    // 그림 이차원 배열의 값이 음수가 되면 M개를 초과하여 그림이 가려진다고 볼 수 있다.

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] picture = new int[101][101];
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= 100; i++) {
            Arrays.fill(picture[i], M);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            for (int x = startX; x <= endX; x++) {
                for (int y = startY; y <= endY; y++) {
                    picture[x][y] -= 1;
                }
            }
        }

        int count = 0;
        for (int x = 1; x <= 100; x++) {
            for (int y = 1; y <= 100; y++) {
                if (picture[x][y] < 0)
                    count++;
            }
        }

        bw.write(String.valueOf(count));

        br.close();
        bw.flush();
        bw.close();
    }
}
