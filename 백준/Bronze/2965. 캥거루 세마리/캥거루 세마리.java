import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 2965번
    // 수학 문제
    // 1차원에서 최소값과 중간의 값, 최대값과 중간의 값 중 더 큰 값을 고르는 문제

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Integer[] nums = new Integer[3];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int bigger = nums[2] - nums[1] - 1;
        int smaller = nums[1] - nums[0] - 1;

        bw.write(bigger > smaller ? String.valueOf(bigger) : String.valueOf(smaller));

        br.close();
        bw.flush();
        bw.close();
    }

}