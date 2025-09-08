import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 15979번
    // 구현, 수학
    // (M,N)의 좌표가 주어질 때 최소한의 순간이동 횟수를 구해야 한다.
    // 그리고 모든 정수 좌표에는 나무가 심어져 있다.
    // 언제나 그렇듯이 직선이 가장 짧기 때문에 직선 위에 정수 해가 몇개 존재하는지만 카운트하면 될듯?
    // (0,0) -> (M,N)의 직선의 방정식을 구하고, 그 위에 정수해가 몇 개인지?
    // M과 N의 범위에 따라 long 타입을 사용해야 함.
    // M과 N이 음수라도, 어차피 직선의 모양은 똑같기 때문에 텔포 횟수는 똑같다.
    // 오답 노트)
    // https://jeongboclass.tistory.com/137
    // 직선을 보는 문제가 아님. 에드 훅 문제로 이 문제만의 특별 알고리즘이 있음.
    // 모든 점을 볼 수 있기 때문에 M - 1, N 이나 M, N - 1로 이동하면 2번 만에 이동할 수 있음.

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long M = Math.abs(Long.parseLong(st.nextToken()));
        long N = Math.abs(Long.parseLong(st.nextToken()));

        long ans = Math.min(getGCD(M, N), 2);

        bw.write(String.valueOf(ans));
        br.close();
        bw.flush();
        bw.close();
    }

    public static long getGCD(long a, long b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        return getGCD(b, a % b);
    }

}
