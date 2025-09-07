import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 11312번
    // 수학
    // 규칙이 있다. 큰 변을 작은 변으로 나누었을 때의 값은 한 번, 그 위층으로 올라가면서 삼각형이 하나씩 줄어들고 중간에 빈 곳을 채우기 위해 줄어든 값만큼 한 번더 채워야 한다.
    // 1
    // 1 + 1 + 2
    // 1 + 1 + 2 + 2 + 3
    // 1 + 1 + 2 + 2 + 3 + 3 + 4
    // 이걸 수식으로 표현하면 결국 (A/B)^2이 된다.
    // 오답 노트)
    // 숫자가 커지면 20억을 넘어서 Integer로 표현할 수 없다.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < tc; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            long ans = (long) (A / B) * (A / B);

            bw.write(String.valueOf(ans) + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }
}



