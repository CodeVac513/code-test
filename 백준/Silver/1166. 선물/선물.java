import java.math.BigInteger;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1166번
    // 구현?
    // 어떤 직육면체 박스를 A*A*A 부피인 N개의 정육면체로 채워야 한다.
    // 이 때 A의 최댓값을 구해야 한다.
    // 부피를 먼저 구하고 세제곱근을 해야 하나? 아니면 제일 작은 변을 기준으로 1씩 줄여보면서 몇 개가 들어가는지 체크?
    // L,W,H의 최대값이 10억인데 10억^3을 자료형에 담을 수 있나? -> 빅인티저를 사용해야 하나?
    // 오답 노트)
    // 바이너리 서치 문제
    // 제일 작은 변을 기준으로 잡는 것은 맞음. 제일 작은 변이 상자가 가질 수 있는 최대값이기에, 0과 그 최대값을 비교하면서 이분탐색으로 값을 찾아나가야 함.

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        double A = 0;
        double maxA = Math.min(L, Math.min(W, H));

        double mid;
        while (A < maxA) {
            mid = (A + maxA) / 2;

            if ((long) (L / mid) * (long) (W / mid) * (long) (H / mid) < N) {
                if (maxA == mid) {
                    break;
                }
                maxA = mid;
            } else {
                if (A == mid) {
                    break;
                }
                A = mid;
            }
        }

        bw.write(String.format("%.15f", A));


        br.close();
        bw.flush();
        bw.close();
    }

}
