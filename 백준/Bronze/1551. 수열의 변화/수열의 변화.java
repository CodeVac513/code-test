import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1551번
    // 구현
    // 문제의 규칙대로 A[N-1] -A[N-2], ... , A[1] - A[0]인 수열 B을 연속적으로 구한다.


    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), ",");
        Integer[] seqA = new Integer[N];

        for (int i = 0; i < N; i++) {
            seqA[i] = Integer.parseInt(st.nextToken());
        }
        Integer[] ans;
        if (K > 0) {
            ans = makeSequence(seqA);
            for (int i = 1; i < K; i++) {
                ans = makeSequence(ans);
            }
        } else {
            ans = seqA;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            sb.append(ans[i]);
            if (i != ans.length - 1) {
                sb.append(",");
            }
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    public static Integer[] makeSequence(Integer[] seqA) {
        int N = seqA.length;

        Integer[] seqB = new Integer[N - 1];

        for (int i = 0; i < N - 1; i++) {
            seqB[i] = seqA[i + 1] - seqA[i];
        }

        return seqB;
    }

}
