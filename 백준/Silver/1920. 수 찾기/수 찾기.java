import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1920번
    // 바이너리 서치
    // 이건 브루트 포스로 풀면 자연 수 N개 중 M개의 수가 있는지 찾아야 하는데,
    // 최악의 경우 10만 * 10만 = 10000000000으로 100억이다.
    // 1초에 약 1억 번의 연산이 가능하므로 100초가 필요하다.
    // 이에 따라 logN의 방법이 있는지 고려해야 하고, 그게 바이너리 서치임.
    // 전제 조건은 정렬이 되어 있어야 함.

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(A);
        int M = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < M; i++) {
            int answer = binarySearch(A, nums[i]) ? 1 : 0;
            bw.write(String.valueOf(answer));
            bw.newLine();
        }


        br.close();
        bw.flush();
        bw.close();
    }

    public static boolean binarySearch(int[] A, int target) {
        int left = 0;
        int right = A.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (A[mid] == target) {
                return true;
            }

            if (A[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return false;
    }
}
