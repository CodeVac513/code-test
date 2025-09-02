import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1969번
    // 문자열
    // Hamming Distance가 최소가 되기 위해서는 각각의 문자열에서 가장 많이 사용되는 글자를 골라서 문자열로 만든다.
    // 오답노트)
    // hamming distance의 합을 안 구했음.
    // 또, hashMap에서 get을 호출한 뒤에 count 값을 1더해야 하는데 그걸 안해서 전부 0으로 저장됨.


    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] dnaArray = new String[N];
        for (int i = 0; i < N; i++) {
            dnaArray[i] = br.readLine();
        }
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for (int i = 0; i < M; i++) {
            HashMap<Character, Integer> alphabets = new HashMap<>();
            for (int j = 0; j < N; j++) {
                alphabets.put(dnaArray[j].charAt(i),
                        alphabets.getOrDefault(dnaArray[j].charAt(i), 0) + 1);
            }
            char current = 'A';
            int max = Integer.MIN_VALUE;
            for (int k = 0; k < 26; k++) {
                Integer count = alphabets.get((char) ('A' + k));
                if (count == null)
                    continue;
                if (max < count) {
                    current = (char) ('A' + k);
                    max = count;
                }
            }
            sum += (N - max);
            sb.append(current);
        }
        bw.write(sb.toString());
        bw.write("\n");
        bw.write(String.valueOf(sum));
        br.close();
        bw.flush();
        bw.close();
    }
}
