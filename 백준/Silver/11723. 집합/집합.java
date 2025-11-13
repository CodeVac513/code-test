import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 11723번
    // 비트마스킹
    // & - and, | - or, ^ - xor, ~ - not
    // << - left shift, 9(00001001) << 1 -> 18(00010010) 왼쪽으로 한 칸 밈, 2를 곱하는 연산으로 생각할 수 있음.
    // >> - right shift, 오른쪽으로 한 칸 밈, 2를 나누고 나머지를 버리는 연산이라고 생각해도 됨.
    // 시프트 연산자가 사칙 연산보다 연산 순위가 낮아서
    // a << 1 + b에서는 1 + b가 먼저 연산된다.
    // (a << 1) + b라고 의도적으로 계산해야 함.
    // a == b & c에서, ==가 and 연산보다 먼저 실행됨.
    // 이런 경우도 a == (b & c) 이런 식으로 연산해야 함.
    // 비트 연산이 들어가면 괄호를 그냥 내가 원하는 대로 쳐버리는 게 나음.

    static int M;
    static int s;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        M = Integer.parseInt(br.readLine());
        s = 0;
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String operation = st.nextToken();
            if (st.countTokens() >= 1) {
                int x = Integer.parseInt(st.nextToken());
                calculate(operation, x);
            } else {
                calculate(operation);
            }
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

    static void calculate(String operation, int x) {
        if (operation.equals("add")) {
            s = (s | (1 << x));
        } else if (operation.equals("remove")) {
            s = (s & ~(1 << x));
        } else if (operation.equals("check")) {
            if ((s & (1 << x)) != 0) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        } else if (operation.equals("toggle")) {
            s = (s ^ (1 << x));
        }
    }

    static void calculate(String operation) {
        if (operation.equals("all")) {
            s = (1 << 21) - 1;
        } else {
            s = 0;
        }
    }
}


