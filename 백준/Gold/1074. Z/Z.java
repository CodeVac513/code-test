import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1074번
    // 재귀
    // 좌표평면으로 투영한 다음, 어느 사분면에 존재하는지만 체크한다면 값을 구할 수 있다.
    // 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래 순서로 탐색하니까 칸의 개수만큼 더해주면 됨.
    // 그렇게 계속 좁혀나가면 값을 구할 수 있음.
    // 오답노트) 사분면에 할당할 때, r과 c에 limit을 빼서 해당 사분면의 어느 좌표 위에 있는지 업데이트해야 함. 
    static int N;
    static int r;
    static int c;
    static long ans;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        recursion(N);
        bw.write(String.valueOf(ans));
        br.close();
        bw.flush();
        bw.close();
    }

    public static void recursion(int N) {
        if (N == 0) {
            return;
        }
        int limit = (int) Math.pow(2, N - 1);
        if (r < limit && c < limit) {
            ans += 0;
            recursion(N - 1);
        } else if (r < limit && c >= limit) {
            ans += limit * limit;
            c -= limit;
            recursion(N - 1);
        } else if (r >= limit && c < limit) {
            ans += limit * limit * 2;
            r -= limit;
            recursion(N - 1);
        } else if (r >= limit && c >= limit) {
            ans += limit * limit * 3;
            r -= limit;
            c -= limit;
            recursion(N - 1);
        }
    }
}
