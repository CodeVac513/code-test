import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 10816번
    // 바이너리 서치
    // N이 500만, M이 500만이므로 브루트포스로 풀면 절대 풀 수 없는 숫자가 나옴.
    // 바이너리 서치를 활용해야 함.
    // `숫자를 찾아라 + 그 숫자의 개수를 세어라.` 이므로 일단 정렬은 필요할 것 같다.
    // 그러면 시작하는 index와 끝나는 index를 구해서 서로 빼면 그 개수를 구할 수 있지 않을까?

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(nums);
        
        int M = Integer.parseInt(br.readLine());
        int[] targets = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < M; i++) {
            bw.write(binarySearch(nums, targets[i]) + " ");
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int firstIndex = -1;
        int lastIndex = -1;
        // 첫번째 index 찾기
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                firstIndex = mid;
            }
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        left = 0;
        right = nums.length - 1;
        // 마지막 index 찾기
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                lastIndex = mid;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                lastIndex = mid;
                left = mid + 1;
            }
        }

        if (firstIndex == -1 || lastIndex == -1) return 0;
        return lastIndex - firstIndex + 1;
    }
}
