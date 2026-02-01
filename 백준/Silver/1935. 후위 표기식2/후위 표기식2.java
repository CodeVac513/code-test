import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1935
    // 트리, postorder 방식
    // 구현은 stack으로 해야 함.
    // 연산자가 나올때까지 숫자를 집어넣고 연산자를 만나면 스택에서 숫자를 꺼내야 함.
    static Stack<Double> stack = new Stack<>();
    static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        String postfix = br.readLine();

        for (int i = 0; i < N; i++) {
            char current = (char) ('A' + i);
            map.put(current, Integer.parseInt(br.readLine()));
        }

        for (int i = 0; i < postfix.length(); i++) {
            char current = postfix.charAt(i);
            if ('A' <= current && current <= 'Z') {
                double operand = map.get(current);
                stack.push(operand);
            } else {
                double a = stack.pop();
                double b = stack.pop();
                double result = calculate(b, a, postfix.charAt(i));
                stack.push(result);
            }
        }
        bw.write(String.format("%.2f", stack.pop()));

        bw.flush();
        bw.close();
        br.close();
    }

    static double calculate(double a, double b, char operator) {
        if (operator == '+') {
            return a + b;
        }

        if (operator== '-') {
            return a - b;
        }

        if (operator== '*') {
            return a * b;
        }

        if (operator== '/') {
            return a / b;
        }

        return 0;
    }
}