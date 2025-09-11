import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 2896번
    // 구현?
    // 칵테일을 만들기 위한 주스의 양이 주어지고,
    // 현재 있는 주스에서 최대한 칵테일을 만들면 얼마나 남는지 계산해야 함.
    // 재료 중 하나가 0이 될 때까지 만들고, 그 비율대로 남는 값을 연산해야 한다.
    // double을 얼마나 잘 사용하는지가 관건일 듯?

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double[] juiceAmount = new double[3];
        for (int i = 0; i < 3; i++) {
            juiceAmount[i] = Double.parseDouble(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        double[] cocktailRecipe = new double[3];
        for (int i = 0; i < 3; i++) {
            cocktailRecipe[i] = Double.parseDouble(st.nextToken());
        }

        double orangeCount = (double) juiceAmount[0] / cocktailRecipe[0];
        double appleCount = juiceAmount[1] / cocktailRecipe[1];
        double pineappleCount = juiceAmount[2] / cocktailRecipe[2];

        double standardCount = Math.min(orangeCount, Math.min(appleCount, pineappleCount));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            double ans = (juiceAmount[i] - cocktailRecipe[i] * standardCount);
            if (canConvertToInteger(ans)) {
                sb.append((int) ans);
            } else {
                sb.append(String.format("%.6f", ans));
            }
            sb.append(" ");
        }

        bw.write(sb.toString());

        br.close();
        bw.flush();
        bw.close();
    }

    public static boolean canConvertToInteger(double num) {
        int converted = (int) num;
        if (num - (double) converted == 0)
            return true;
        return false;
    }
}

