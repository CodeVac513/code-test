import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 2960번
    // 구현, 브루트포스, 에라토스테네스의 체
    // 문제에 나와있는 대로 알고리즘을 구현하면서, count 변수를 추가하여 K와 같은지 비교하면 될 것 같다.
    // N의 크기는 1000이하 -> 배열의 크기를 1001로 잡으면 index를 통해 구할 수 있을 듯?
    static int count;
    static int target;
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        count = 0;
        target = 0;
        Integer[] nums = new Integer[1001];
        Arrays.fill(nums, -1);

        for (int i = 2; i <= N; i++) {
            if (nums[i] == -1) {
                nums[i] = 0;
                checkNums(nums, i);
            }
        }

        bw.write(String.valueOf(target));
        br.close();
        bw.flush();
        bw.close();
    }

    public static void checkNums(Integer[] nums, Integer current) {
        for (int i = current; i <= N; i += current) {
            if (nums[i] != 1) {
                nums[i] = 1;
                count++;
                if (count == K)
                    target = i;
            }
        }
    }
}
