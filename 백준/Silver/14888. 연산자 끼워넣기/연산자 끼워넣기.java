import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 14888번
    // 백트래킹
    // 이건 모든 경우의 수를 다 봐야 하는 문제이다.
    // 덧셈, 뺄셈, 곱셈의 개수가 매번 다르기에 그리디에서 말하는 최적의 해를 매번 생성한다는 보장을 할 수가 없다.
    // 문제 조건에서 항상 숫자들은 -10억 이상, 10억 이하 -> integer를 사용하라는 의미
    // 오답 노트)
    // nums의 index로 depth를 사용하고 있는데, depth == nums.length로 잘못된 종료 조건을 설정함.
    // nums.length - 1로 설정해야 함.
    // 오답 노트2)
    // 기존 방식대로 expression(수식)을 만들어서 넘기고, 그걸 파싱할 때 ""를 기준으로 1글자씩 쪼개면
    // 두 자리 이상의 숫자는 연산이 불가능. " " 공백을 하나 두고, 파싱할 수 있도록 수정.
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int[] operators;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        operators = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        backtracking(nums, "", 0);

        bw.write(String.valueOf(max));
        bw.newLine();
        bw.write(String.valueOf(min));

        br.close();
        bw.flush();
        bw.close();
    }

    static void backtracking(int[] nums, String expression, int depth) {
        if (depth == nums.length - 1) {
            int result = parseExpression(expression + nums[depth]);
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for (int i = 0; i < 4; i++) {
            String nextExpression = expression + nums[depth] + " ";
            if (operators[i] != 0) {
                operators[i]--;
                switch (i) {
                    case 0:
                        nextExpression += "+ ";
                        break;
                    case 1:
                        nextExpression += "- ";
                        break;
                    case 2:
                        nextExpression += "* ";
                        break;
                    case 3:
                        nextExpression += "/ ";
                        break;
                    default:
                        break;
                }
                backtracking(nums, nextExpression, depth + 1);
                operators[i]++;
            }
        }
    }

    static int parseExpression(String expression) {
        String[] tokens = expression.trim().split(" ");
        Queue<String> q = new LinkedList<>();

        for (String token : tokens) {
            q.offer(token);
        }
        int num = Integer.parseInt(q.poll());
        String operator = q.poll();
        while (!q.isEmpty()) {
            String currentToken = q.poll();

            switch (currentToken) {
                case "+":
                    operator = currentToken;
                    break;
                case "-":
                    operator = currentToken;
                    break;
                case "*":
                    operator = currentToken;
                    break;
                case "/":
                    operator = currentToken;
                    break;
                default:
                    num = calculate(num, Integer.parseInt(currentToken), operator);
            }
        }

        return num;
    }

    public static int calculate(int num, int x, String operator) {
        int result = 0;
        if (operator.equals("+")) {
            result = num + x;
        } else if (operator.equals("-")) {
            result = num - x;
        } else if (operator.equals("*")) {
            result = num * x;
        } else if (operator.equals("/")) {
            if (num < 0) {
                num -= 2 * num;
                result = -(num / x);
            } else {
                result = num / x;
            }
        }
        return result;
    }
}
