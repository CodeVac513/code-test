import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 20921번
    // 구현 문제인가?
    // 문제에서 주어진 정의를 활용해서 어떤 규칙을 뽑아낼 수 있나?
    // 힌트 사용 -> 그리디 문제
    // 1. 가장 큰 숫자부터 선택하며 관계의 합을 구한다.
    // 2. 현재 합이 K를 초과하면 다음으로 작은 숫자를 선택한다.
    // 1과 2를 반복.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int currentRelationCount = 0;
        int currentIndex = N - 1;
        Boolean[] selectedNums = new Boolean[N];
        Arrays.fill(selectedNums, false);

        while (currentIndex > 0) {
            if (currentRelationCount + currentIndex <= K) {
                currentRelationCount += currentIndex;
                selectedNums[currentIndex] = true;
            }
            currentIndex--;
        }

        for (int i = N - 1; i >= 0; i--) {
            if (selectedNums[i]) {
                {
                    bw.write((i + 1) + " ");
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (!selectedNums[i]) {
                bw.write((i + 1) + " ");
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }


}