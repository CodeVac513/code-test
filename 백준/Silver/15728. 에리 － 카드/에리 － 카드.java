import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    // 15728번 문제
    // 정렬문제
    // 공유 숫자 카드와 팀 숫자 카드를 오름차순으로 정렬
    // 팀 숫자 카드가 음수라면 공유 숫자에서 가장 작은 값과 곱해야 크기가 커진다.
    // 양수라면 공유 숫자에서 가장 큰 값과 곱해야 크기가 커진다.

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Integer[] shared = new Integer[N];
        Integer[] team = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
            shared[j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
            team[j] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> results = new ArrayList<>();
        Arrays.sort(shared);
        Arrays.sort(team);

        for (int i = 0; i < N; i++) {
            if (team[i] < 0) {
                int multiplied = team[i] * shared[0];
                results.add(multiplied);
            } else {
                int multiplied = team[i] * shared[N - 1];
                results.add(multiplied);
            }
        }

        Collections.sort(results);

        bw.write(Integer.toString(results.get(N - 1 - K)));


        bw.flush();
        bw.close();
        br.close();
    }
}
