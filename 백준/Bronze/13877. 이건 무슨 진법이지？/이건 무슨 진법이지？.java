import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 13877번
    // 구현?
    // 숫자 하나를 입력 받으면 그 숫자를 8진수와 16진수로도 표현해야 함.
    // 오답 노트)
    // 숫자가 각각 8진수, 16진수로 나타내진 수라고 가정해야 함. 십진수 num을 다른 진법으로 나타내는 문제가 아님.
    // 오답 노트2)
    // 자릿수에 8이 오는 경우도 제외해야 함.
    // 오답 노트3)
    // 0으로 시작하는 경우를 지우기 위해 num을 integer로 변환했다가 다시 String으로 변환

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= testCase; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            bw.write(index + " " + octalToDecimal(String.valueOf(num)) + " " + num + " " + hexadecimalToDecimal(String.valueOf(num)));
            bw.write("\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    public static int octalToDecimal(String num) {
        int sum = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            int currentNum = Character.getNumericValue(num.charAt(num.length() - 1 - i));
            if (currentNum >= 8) {
                return 0;
            }
            sum += (currentNum * (int) Math.pow(8, i));
        }

        return sum;
    }

    public static int hexadecimalToDecimal(String num) {
        int sum = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            int currentNum = Character.getNumericValue(num.charAt(num.length() - 1 - i));
            sum += (int) (currentNum * Math.pow(16, i));
        }
        return sum;
    }

}