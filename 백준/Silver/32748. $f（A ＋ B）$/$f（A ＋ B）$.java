import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 32748번
    // 구현 문제
    // 십진수 x와 그에 대응하는 y라는 값이 주어지고, f(A), f(B)가 주어지면 역으로 A와 B를 구하고 A+B에 대응하는 값을 구하는 문제.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Integer, Integer> func = new HashMap<>();
        Map<Integer, Integer> reverseFunc = new HashMap<>();

        String[] numSet = br.readLine().split(" ");
        for(int i = 0 ; i < 10 ; i++) {
            reverseFunc.put(Integer.parseInt(numSet[i]), i);
            func.put(i,Integer.parseInt(numSet[i]));
        }

        String[] nums = br.readLine().split(" ");
        Integer sum = 0;
        for(int testCase = 0 ; testCase < 2 ; testCase++) {
            String[] digitNums = nums[testCase].split("");
            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < digitNums.length ; i++) {
                sb.append(reverseFunc.get(Integer.parseInt(digitNums[i])));
            }
            sum += Integer.parseInt(sb.toString());
        }

        String sumToString = Integer.toString(sum);
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < sumToString.length() ; i++) {
            sb.append(func.get(Character.getNumericValue(sumToString.charAt(i))));
        }

        bw.write(sb.toString());


        br.close();
        bw.flush();
        bw.close();
    }
}