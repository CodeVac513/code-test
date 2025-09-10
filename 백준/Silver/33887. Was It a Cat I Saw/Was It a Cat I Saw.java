import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 33887번
    // 문자열, 구현, DP?
    // 문제에서 주어진대로 잘 구현하면 된다.
    // 십진수 정수를 입력받고 그 정수를 이진수의 문자열로 표현했을 때 펠린드롬인지 아닌지를 판별한다.
    // 모든 X 범위의 숫자의 펠린드롬 여부를 확인해서 배열로 만들어놓고 index가 얼마 차이나는지 체크한다면?
    // => 10억번의 연산을 해야하므로, 1초에 약 1억번 연산이라 가정하면 시간 초과 발생할 듯?
    // 그냥 현재 입력받은 값이 펠린드롬이 아니라면 반복문을 통해서 X에 1을 더하던 빼던 값을 계속 구하면서 가장 가까운 펠린드롬 숫자를 찾는 수밖에 없을 듯?

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            int num = Integer.parseInt(br.readLine());
            String input = decimalToBinary(num);

            if (isPalindrome(input)) {
                bw.write("0\n");
            } else {
                bw.write(countToPalindrome(num) + "\n");
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static boolean isPalindrome(String input) {
        String reversed = new StringBuilder(input).reverse().toString();
        if (input.equals(reversed)) {
            return true;
        }

        return false;
    }

    public static String decimalToBinary(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(num % 2);
            num /= 2;
        }
        return sb.reverse().toString();
    }

    public static int countToPalindrome(int num) {

        int numPlusOne = num;
        int numMinusOne = num;
        int countPlus = 0;
        int countMinus = 0;

        while (!isPalindrome(decimalToBinary(numPlusOne))) {
            numPlusOne++;
            countPlus++;
        }

        while (!isPalindrome(decimalToBinary(numMinusOne))) {
            numMinusOne--;
            countMinus++;
            if (numMinusOne <= 0) {
                countMinus = Integer.MAX_VALUE;
                break;
            }
        }
        return countPlus < countMinus ? countPlus : countMinus;
    }

}
