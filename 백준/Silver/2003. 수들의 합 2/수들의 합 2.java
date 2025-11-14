import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 2003번
    // 투포인터
    // 시작점에서부터 1개씩 포인터를 늘려서 합이 M이 되는 경우를 구한다.
    // 경우를 구했으면 answer++, M이 넘어간다면 answer 값 변화없이 시작점을 1 늘린다.

    static int N;
    static int M;
    static int[] nums;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static public void solve() {
        int startIndex = 0;

        while (startIndex < N) {
            int sum = 0;
            for (int i = startIndex; i < N; i++) {
                sum += nums[i];
                if (sum == M) {
                    answer++;
                    break;
                }

                if (sum > M) {
                    break;
                }
            }
            startIndex++;
        }
    }
}


