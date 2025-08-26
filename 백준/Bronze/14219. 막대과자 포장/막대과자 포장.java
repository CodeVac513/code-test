import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 14219번
    // 수학
    // ㅡ 모양이던, ㄴ 모양이던 타일의 개수가 3의 배수일 때만 채울 수 있다.

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int flag = (N * M) % 3;
        if (flag == 0) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
