import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 8949번
    // 구현 문제
    // 각 자리의 덧셈에서 올림이 발생해도 그냥 적은 뒤에 이어 버린다.
    // 각 자리를 더해서 StringBuilder 따위에 append로 이어버리면 될 것
    // 주의 사항)
    // 길이가 다른 숫자를 더하는 경우, 먼저 남는 자릿수를 문자열에 더한다.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = br.readLine().split(" ");

        String longer, shorter;
        if (nums[0].length() >= nums[1].length()) {
            longer = nums[0];
            shorter = nums[1];
        } else {
            longer = nums[1];
            shorter = nums[0];
        }

        StringBuilder sb = new StringBuilder();

        Integer lengthDifference = longer.length() - shorter.length();

        for (int i = 0; i < lengthDifference; i++) {
            sb.append(longer.charAt(i));
        }

        for (int i = 0; i < shorter.length(); i++) {
            int sum = Character.getNumericValue(shorter.charAt(i)) + Character.getNumericValue(
                    longer.charAt(lengthDifference + i));
            sb.append(sum);
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }
}