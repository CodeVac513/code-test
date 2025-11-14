import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1806번
    // 투포인터
    // N짜리 수열에서 연속된 수들의 부분합을 구하되, 가장 짧은 길이를 구하는 프로그램
    // 시작점과 끝지점을 다르게 운영한다.
    // S미만일 때는 sum에 nums[end]를 더해서 S 이상이 될 때까지 더해나간다.
    // S이상이 되면, 최소 길이를 구하기 위해 nums[start]를 빼면서 start를 end와 가깝게 설정한다.
    // 그러다가 다시 S미만이 되면 end를 뒤로 밀기 시작한다.
    // 이렇게 반복하다보면 end가 N에 도달할텐데 이 때 반복문을 탈출한다.

    static int N;
    static int S;
    static int[] nums;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        bw.write(String.valueOf(answer == Integer.MAX_VALUE ? 0 : answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static public void solve() {
        int startIndex = 0;
        int endIndex = 0;
        int sum = 0;
        while (true) {
            if (sum >= S) {
                answer = Math.min(answer, endIndex - startIndex);
                sum -= nums[startIndex];
                startIndex++;
            } else if (endIndex == N) {
                break;
            } else {
                sum += nums[endIndex];
                endIndex++;
            }
        }
    }
}


