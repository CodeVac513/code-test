import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1094번
    // 비트마스킹
    // & - and, | - or, ^ - xor, ~ - not
    // << - left shift, 9(00001001) << 1 -> 18(00010010) 왼쪽으로 한 칸 밈, 2를 곱하는 연산으로 생각할 수 있음.
    // >> - right shift, 오른쪽으로 한 칸 밈, 2를 나누고 나머지를 버리는 연산이라고 생각해도 됨.
    // 이 문제는 막대기를 실제로 자르는 시뮬레이션보다, 이진수 표현에서 1의 개수를 세는 문제에 가까움.

    static int X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        X = Integer.parseInt(br.readLine());

        bw.write(String.valueOf(Integer.bitCount(X)));
        bw.flush();
        bw.close();
        br.close();
    }
}


