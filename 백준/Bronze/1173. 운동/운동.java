import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1173번
    // 그리디?
    // 운동을 할 수 있는 상태에는 운동을 하고, 할 수 없다면 휴식을 취한다.
    // 최대한 짧은 시간 안에 운동 시간 N을 채워야 하기 때문. (운동을 할 수 있을 때 하는 것이 최적의 해)

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int current = m;

        if (current + T > M) {
            bw.write("-1");
        } else {
            int count = 0;
            int time = 0;
            while (count != N) {
                if (current + T <= M) {
                    count++;
                    current += T;
                } else {
                    current -= R;
                    if (current < m)
                        current = m;
                }
                time++;
            }
            bw.write(String.valueOf(time));
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
