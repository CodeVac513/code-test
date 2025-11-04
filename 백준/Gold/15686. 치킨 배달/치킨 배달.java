

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 15686번
    // 백트래킹
    // 이 문제는 조합 문제임.
    // Combination을 통해서 치킨집의 위치를 뽑고, 각각 치킨집 조합 중 가장 짧은 거리를 구하면 됨.
    static int N;
    static int M;
    static int answer = Integer.MAX_VALUE;
    static List<int[]> chickens = new ArrayList();
    static List<int[]> houses = new ArrayList();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int currentInfo = Integer.parseInt(st.nextToken());

                if (currentInfo == 1) {
                    houses.add(new int[] {i, j});
                }

                if (currentInfo == 2) {
                    chickens.add(new int[] {i, j});
                }
            }
        }

        backtrack(0, new ArrayList<>());

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static void backtrack(int startIndex, ArrayList<int[]> selected) {
        if (selected.size() == M) {
            int distance = calculateDistance(selected);
            answer = Math.min(distance, answer);
            return;
        }

        for (int i = startIndex; i < chickens.size(); i++) {
            selected.add(chickens.get(i));
            backtrack(i + 1, selected);
            selected.remove(selected.size() - 1);
        }

    }

    static int calculateDistance(ArrayList<int[]> selected) {

        int sum = 0;
        for (int[] housePoint : houses) {
            int distance = Integer.MAX_VALUE;
            for (int[] chickenPoint : selected) {
                int temp = Math.abs(housePoint[0] - chickenPoint[0])
                        + Math.abs(housePoint[1] - chickenPoint[1]);

                distance = Math.min(distance, temp);
            }
            sum += distance;
        }

        return sum;
    }
}
