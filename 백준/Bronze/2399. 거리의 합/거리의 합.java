import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 2399번
    // 수학, 구현
    // index 0부터 1까지 모든 길이를 구한다. (abs로)
    // 그렇게 모든 조합을 구하면 i,j를 구했는 것이다.
    // j,i도 모두 구해야하므로 결과 * 2를 해준다.
    // 오답노트)
    // 10^9까지라서 합을 Long 타입으로 받아야 한다.
    // Long 타입으로도 안되는 듯(메모리 초과 발생)
    // BigInteger로 받아보자.
    // 중첩 반복문으로 더하려고 해서 메모리 초과가 계속 발생하나?수학적으로 최적화할 수 있을까?
    // 그냥 곱하기 2를 하지 말고 모든 경우를 구해볼까?
    // nums를 Long으로 선언해서 메모리가 부족한가?
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        long sum = 0L;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += (long) Math.abs(nums[i] - nums[j]);
            }
        }

        bw.write(String.valueOf(sum));

        br.close();
        bw.flush();
        bw.close();
    }
}
