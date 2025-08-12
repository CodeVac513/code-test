import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    // 16676번
    // 구현 문제
    // N이라는 값이 입력되었을 때, 1이 몇 번 반복되는지를 체크한다.
    // 1은 최초로 여러 번 반복되는 경우, 그 반복 횟수 만큼 카드를 구매해야 만들 수 있는 수이기 때문이다.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String goal = br.readLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < goal.length(); i++) {
            sb.append(1);
        }

        int result = Integer.parseInt(goal) >= Integer.parseInt(sb.toString()) ? goal.length()
                : goal.length() - 1;
        if (Integer.parseInt(goal) == 0)
            result = 1;
        bw.write(result + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
