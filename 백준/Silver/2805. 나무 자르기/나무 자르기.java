import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 2805번
    // 파라메트릭 서치, 바이너리 서치
    // M의 길이 상한은 20억, integer 범위 내에서 해결 가능
    // 최적화 문제는 NP 문제, 이걸 결정 문제로 끌어내리려면 어떤 길이를 제시해야한다,
    // 그래서 바이너리 서치를 통해 제시된 길이를 검사하는 것이다.
    // 정리하면) M미터의 나무를 얻을 수 있는 최적의 길이는 얼마인가?
    // -> X미터에서 M미터의 나무를 얻을 수 있는가?
    // "최댓값/최솟값을 구하라"는 최적화 문제를 "이 값이 가능한가?"라는 결정 문제로 바꾸기
    // 따라서 이 문제의 핵심은 isPossible()이라는 결정 함수를 두는 게 포인트임.

    // 오답 노트
    // isPossible에서 sum += heights[i] - h;를 무조건 연산하는게 아니라, 절단기보다 나무가 높은 경우에만 더해야 함.
    // 그리고 sum을 구하는 과정에서 큰 값을 고려하여 long 타입으로 선언.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        int[] heights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        bw.write(String.valueOf(parametricSearch(M, heights)));

        br.close();
        bw.flush();
        bw.close();
    }

    public static int parametricSearch(long M, int[] heights) {
        int left = 0;
        int right = 1000000000;
        int answer = 0;

        while (left <= right) {
            int h = left + (right - left) / 2;
            if (isPossible(h, heights, M)) {
                answer = h;
                left = h + 1;
            } else {
                right = h - 1;
            }
        }

        return answer;
    }

    public static boolean isPossible(int h, int[] heights, long M) {
        long sum = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] - h > 0) {
                sum += heights[i] - h;
            }
        }

        return sum >= M ? true : false;
    }

}
