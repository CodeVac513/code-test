import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 1654번
    // 이진탐색 문제
    // 탐색 범위는 1~가장 긴 케이블의 길이
    // mid(중간값)는 left + right를 2로 나눈 값
    // mid로 케이블을 나눴을 때, 만들어진 개수가 N개 이상이면 더 긴 길이를 탐색할 수 있도록 left = mid + 1로 설정
    // N개 미만이면 더 짧게 가져가기 위해 right = mid - 1로 설정
    
    // 오답 노트)
    // 랜선의 길이가 2^31 - 1 이하이므로, left + right가 Integer 범위를 벗어날 수 있다.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] lan = new int[K];
        for (int i = 0; i < K; i++) {
            lan[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(lan);
        long left = 1, right = lan[K - 1];
        long answer = 0;
        while (left <= right) {

            long mid = (left + right) / 2;
            long count = 0;

            for (int cable : lan) {
                count += cable / mid;
            }

            if (count >= N) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        bw.write(Long.toString(answer));
        br.close();
        bw.flush();
        bw.close();
    }
}