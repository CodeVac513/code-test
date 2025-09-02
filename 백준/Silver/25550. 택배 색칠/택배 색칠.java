import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 25550번
    // 수학?, 구현?
    // 동서남북 방향으로 색을 칠할 때, 가장 높이가 낮은 기둥은 색칠을 피할 수 있다.
    // 각각의 기둥에서 가장 위에 있는 것들을 무조건 색칠된다.
    // 높이가 가장 낮은 기둥을 기준으로 가장 위에 있는 것들을 빼면 된다.
    // 이 규칙은 N과 M이 3이상일 때만 사용할 수 있다. (둘 중 하나라도 2 이하의 값이라면 무조건 0이다.)
    // 예를 들어, 4 * 4 모양으로 쌓여있다면 내부의 2*2만 살릴 수 있다.
    // 4 * 3 모양이라면 1 * 2, 2개의 기둥만 살릴 수 있다.
    // 규칙을 뽑아내면 각 변에서 2만큼의 마진을 필요로 하므로 (N - 2) * (M - 2)로 생각하면 된다.
    // 추가)
    // 내가 생각했던 대로 가장 낮은 높이만 찾아서 계산한다면 감싸진 것을 제대로 연산할 수 없다.
    // 예를 들어, 정면에서 보았을 때 5, 3, 5, 5, 5인 택배 배열이 있다면 분명 4번째 5에서 4개는 보호가 될 수도 있는데 3으로 책정됨.
    // 그냥 모든 층을 다 검사해야 할 수 도 있나? -> 그러면 층이 1억갠데, 1초에 딱 맞춰지지 않을까?
    // 위에서부터 노출이 안되는 부분까지 내려가면서 노출되는 부분만 제외시키면 더 빨리 계산할 수 있다.
    // 오답노트)
    // 만약 1억개가 1000개씩 1과 섞여서 쌓여있다면 1억 * 10 ^ 3 * 10 ^ 3 => 10 ^ 15만큼 연산할 수도 있음. 따라서 시간 초과가 발생함.
    //

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] boxes = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                boxes[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Long ans = 0L;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                // 4방향 최솟값 구하기
                int minAdjacent = Math.min(Math.min(boxes[i - 1][j], boxes[i + 1][j]),
                        Math.min(boxes[i][j - 1], boxes[i][j + 1]));

                // 보호받는 층 수 = min(현재높이-1, 4방향최솟값)
                int protectedLayers = Math.min(boxes[i][j] - 1, minAdjacent);
                ans += Math.max(0, protectedLayers);
            }
        }


        bw.write(String.valueOf(ans));


        br.close();
        bw.flush();
        bw.close();
    }
}
