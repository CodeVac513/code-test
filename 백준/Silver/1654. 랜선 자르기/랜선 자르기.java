import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1654번
    // 파라메트릭 서치, 바이너리 서치
    // 나무 자르기 문제와 매우 비슷하다.
    // 최대 랜선, 즉 최적화된 값 X를 구해야하는데 파라메트릭 서치를 사용해서 결정 문제로 끌어내려야 한다.
    // isPossible의 조건만 잘 생각하자.

    // 오답 노트) 시간 초과 발생
    // left가 1이상이어야 하는데, 0으로 설정해서 무한 루프가 발생될 것 같음.
    // 2) 여전히 시간 초과 발생
    // 값이 매우 커지면서 answer 부분에 오버플로가 발생하고 무한 루프가 도는건가?

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] A = new int[K];
        int longest = 0;
        for (int i = 0; i < K; i++) {
            A[i] = Integer.parseInt(br.readLine());
            if (A[i] > longest) {
                longest = A[i];
            }
        }
        Arrays.sort(A);
        bw.write(String.valueOf(parametricSearch(N, A, longest)));

        br.close();
        bw.flush();
        bw.close();
    }

    public static long parametricSearch(int N, int[] A, int longest) {
        long left = 1, right = longest;
        long answer = 0;
        while (left <= right) {
            long length = left + (right - left) / 2;

            if (isPossible(N, A, length)) {
                answer = length;
                left = length + 1;
            } else {
                right = length - 1;
            }
        }
        return answer;
    }

    public static boolean isPossible(int N, int[] A, long length) {
        long sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i] / length;
        }

        return sum >= N ? true : false;
    }


}
