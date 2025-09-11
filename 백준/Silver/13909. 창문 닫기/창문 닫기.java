import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 13909번
    // 일종의 수학?
    // 약수의 개수가 짝수면 창문이 닫히고, 약수의 개수가 홀수면 창문이 열린다.
    // 처음에는 브루트포스로 21억개의 약수 개수를 구하려고 했는데, 시간 초과가 날 게 뻔하다.
    // 약수가 홀수인건 제곱수라는 아이디어를 사용해서 제곱이 21억을 넘어가지 않는
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int num = 1;
        int count = 0;
        while (true) {
            long squareNum = num * num;
            if (squareNum > 2100000000 || squareNum > N)
                break;
            count++;
            num++;
        }

        bw.write(String.valueOf(count));
        br.close();
        bw.flush();
        bw.close();
    }


}

