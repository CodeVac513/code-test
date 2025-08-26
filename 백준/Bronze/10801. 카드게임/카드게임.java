import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 10801번
    // 구현
    //
    // 그냥 주어진 숫자를 비교해서 좌가 큰지, 우가 큰지 카운트하고 그 값을 비교하면 됨.
    // cf)
    // 문제를 언뜻 읽었을 때 A가 내는 카드 목록 혹은 B가 내는 카드 목록이 주어졌을 때,
    // 이길 수 있는 최선의 수를 작성하는 문제인 줄...

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Integer[] aNums = new Integer[10];
        for (int i = 0; i < 10; i++) {
            aNums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        Integer[] bNums = new Integer[10];
        for (int i = 0; i < 10; i++) {
            bNums[i] = Integer.parseInt(st.nextToken());
        }

        int aCount = 0;
        int bCount = 0;

        for (int i = 0; i < 10; i++) {
            if (aNums[i] > bNums[i])
                aCount++;
            else if (bNums[i] > aNums[i])
                bCount++;
        }

        bw.write(aCount > bCount ? "A" : bCount > aCount ? "B" : "D");

        br.close();
        bw.flush();
        bw.close();
    }
}
