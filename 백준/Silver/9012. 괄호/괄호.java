import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 9012
    // 자료 구조, queue

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String[] input = br.readLine().split("");
            int N = input.length;
            boolean isValid = true;
            int countLeft = 0;
            int countRight = 0;
            for (int i = 0; i < N; i++) {
                if (countLeft < countRight) {
                    isValid = false;
                    break;
                }
                if (input[i].equals("(")) {
                    countLeft++;
                    continue;
                }
                if (input[i].equals(")")) {
                    countRight++;
                }
            }

            if (countLeft != countRight) isValid = false;


            if (isValid) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}