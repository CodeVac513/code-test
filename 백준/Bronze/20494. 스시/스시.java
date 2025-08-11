import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 20294번
    // 스시는 26가지의 종류가 있으며 N + 1 개의 접시로 이루어져 있다.
    // N명의 사람이 원하는 스시리스트를 제공하는데, 모든 사람이 식사를 마치기 위해 셰프가 만들어야 하는 스시의 최소 개수를 구해야 한다.
    // 스시를 누가 언제 최대한 빠르게 먹어야 하는지는 고려하지 않기 때문에 무슨 종류의 스시가 리스트에 있는지만 체크하면 해결할 수 있는 문제 아닐까?
    // 한 종류의 스시가 한 손님의 목록에 두 번 이상 속할 수 있음에 유의하라. <- 이런 조건이 있는데, Set을 사용하지 말라는 의미아닐까 싶다.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer N = Integer.parseInt(br.readLine());

        List<String> sushiList = new ArrayList<>();

        for(int i = 0 ; i < N ; i++) {
            String[] sushi = br.readLine().split("");
            sushiList.addAll(Arrays.asList(sushi));
        }

        bw.write(sushiList.size()+"\n");

        br.close();
        bw.flush();
        bw.close();
    }
}