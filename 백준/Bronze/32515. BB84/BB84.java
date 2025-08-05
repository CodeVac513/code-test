import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String firstBase = br.readLine();
        int[] firstNums = makeNumsArrayFromString(br.readLine().split(""), N);

        String secondBase = br.readLine();
        int[] secondNums = makeNumsArrayFromString(br.readLine().split(""), N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (firstBase.charAt(i) == secondBase.charAt(i)) {
                if (firstNums[i] == secondNums[i]) {
                    sb.append(firstNums[i] + "");
                } else {
                    sb = new StringBuilder();
                    sb.append("htg!");
                    break;
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int[] makeNumsArrayFromString(String[] nums, int N) {

        int[] numsArray = new int[N];
        for (int i = 0; i < N; i++) {
            numsArray[i] = Integer.parseInt(nums[i]);
        }

        return numsArray;
    }
}
