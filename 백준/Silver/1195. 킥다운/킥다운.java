import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1195번
    // 구현, 문자열
    // 2와1, 1과2, 1과1은 같은 index에 위치할 수 있다.
    // 두 개의 기어 파트 사이에 가장 많은 겹침이 발생해야 한다.
    // 길이가 짧거나 같은 파트를 하나 잡아서 1/2, 2/1, 1/1 세 가지 케이스로 잘 겹쳐질 수 있도록 만들어야 한다.
    // 투 포인터를 사용해서 긴 파트를 잘라내서 짧은 파트와 비교하고 몇개가 겹친느지 확인한다.
    // 그리고 긴 파트 길이 + 짧은 파트 길이 - 겹친 길이 = 기대값

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String part1 = br.readLine();
        String part2 = br.readLine();
        int minLength = Integer.MAX_VALUE;

        minLength = Math.min(minLength, findMinLength(part1, part2));

        minLength = Math.min(minLength, findMinLength(part2, part1));

        bw.write(String.valueOf(minLength));

        br.close();
        bw.flush();
        bw.close();
    }

    public static int findMinLength(String topGear, String bottomGear) {
        int minLen = topGear.length() + bottomGear.length();

        for (int offset = 0; offset <= topGear.length(); offset++) {
            if (canFit(topGear, bottomGear, offset)) {
                int totalLength = Math.max(topGear.length(), offset + bottomGear.length());
                minLen = Math.min(minLen, totalLength);
            }
        }
        return minLen;
    }

    public static boolean canFit(String topGear, String bottomGear, int offset) {

        for (int i = 0; i < bottomGear.length(); i++) {
            int topIndex = offset + i;
            if (topIndex < topGear.length()) {
                if (topGear.charAt(topIndex) == '2' && bottomGear.charAt(i) == '2')
                    return false;
            }
        }

        return true;
    }
}

