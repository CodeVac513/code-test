import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 25972번
    // 구현 문제
    // 현재 도미노의 위치와 길이를 더한 값이 다음 도미노를 건드릴 수 있는지를 판별하는 문제.
    // 건드릴 수 없다면 손으로 직접 무너트려야 하기 때문에 count + 1
    // 오답 노트)
    // 도미노의 X좌표 순서가 오름차순이 아닐 수도 있음.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer N = Integer.parseInt(br.readLine());

        Integer[][] dominoInfo = new Integer[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dominoInfo[i][0] = Integer.parseInt(st.nextToken());
            dominoInfo[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(dominoInfo, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        int count = 1;
        for (int i = 0; i < N - 1; i++) {
            int currentLength = dominoInfo[i][0] + dominoInfo[i][1];
            int nextPosition = dominoInfo[i + 1][0];
            if (currentLength < nextPosition) {
                count++;
            }
        }

        bw.write(String.valueOf(count));
        br.close();
        bw.flush();
        bw.close();
    }
}