import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 2828번
    // 투포인터?
    // 시작하는 인덱스와 끝나는 인덱스를 설정한다. (초기에는 1과 M이겠지)
    // 그래서 index 범위에 사과가 들어오는지 확인한다.
    // 들어온다면 현재 유지, 들어오지 않는다면 가장 끝의 두 인덱스에서 받을 수 있도록 옮긴다.
    // 좀 더 복잡하게 고려해서 미리 몇 칸을 움직인다면 이득을 볼 수 있을 거라 생각이 들 수도 있다.
    // 시간 상으로는 이득을 볼 수 있으나 결국 움직이는 거리는 똑같을 것이기에 지금은 당장에 사과가 들어가는 것만 체크하면 될 것 같은데? (이 문제는 시간을 고려하지는 않으니까)
    // 오답 노트)
    // 사과가 바구니 왼쪽, 오른쪽에 있는 경우 맞추는 로직이 좀 불안정하게 짜여져 있음.
    // 처음에 너무 복잡하게 생각한 듯함. 단순하게 사과가 바구니 왼쪽에 있는지, 오른쪽에 있는지만 검색하면 됨. 둘을 빼고 어디가 최소값이며 Math.abs로 절대값을 구하는 등의 로직은 필요없을 것 같음.

    static int startIndex;
    static int endIndex;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int J = Integer.parseInt(br.readLine());
        startIndex = 1;
        endIndex = M;
        int totalDistance = 0;
        for (int testCase = 0; testCase < J; testCase++) {
            int appleDropIndex = Integer.parseInt(br.readLine());

            if (!canReceiveApple(startIndex, endIndex, appleDropIndex)) {
                totalDistance += calculateDistance(appleDropIndex, M);
            }
        }
        bw.write(String.valueOf(totalDistance));
        br.close();
        bw.flush();
        bw.close();
    }

    static boolean canReceiveApple(int startIndex, int endIndex, int appleDropIndex) {
        if (appleDropIndex >= startIndex && appleDropIndex <= endIndex) {
            return true;
        }
        return false;
    }

    static int calculateDistance(int appleDropIndex, int basketSize) {
        if (appleDropIndex < startIndex) {
            int distance = startIndex - appleDropIndex;
            startIndex = appleDropIndex;
            endIndex = appleDropIndex + basketSize - 1;
            return distance;
        } else {
            int distance = appleDropIndex - endIndex;
            endIndex = appleDropIndex;
            startIndex = appleDropIndex - basketSize + 1;
            return distance;
        }
    }

}
