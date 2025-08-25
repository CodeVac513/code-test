import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 7774번
    // 그리디
    // 첫 번째 멀티탭 (A to B) n개와 두 번째 멀티탭 (B to A) m개를 잘 조합해서 표준 A 콘센트를 최대 개수만큼 만들기
    // 일단 가장 많은 구멍이 있는 멀티탭을 사용하도록 하면 최선의 개수를 만들 수 있을 것.
    // 오답 노트)
    // 내림차순으로 정렬을 해야 하는데, 오름차순으로 정렬함.
    // 오답 노트2)
    // 로직을 조금 수정하자.
    // 첫 번째 멀티탭 중 가장 많은 구멍의 개수 만큼 두 번째 멀티탭을 꽂는다.
    // 만약 멀티탭이 남이 있다면 위의 과정을 반복하는 로직을 수정한다.
    // 현재 작성된 로직은 두 번째 멀티탭이 남은 경우만 고려하는데 둘 모두가 남아야 한다.
    // 오답 노트3)
    // i <  firstMultiTaps.length은 항상 참이다. 다음 멀티탭이 있는지 확인해야 하므로 i + 1 < firstMultiTaps.length로 수정해야 한다.

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Integer[] firstMultiTaps = new Integer[n];
        Integer[] secondMultiTaps = new Integer[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < firstMultiTaps.length; i++) {
            firstMultiTaps[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < secondMultiTaps.length; i++) {
            secondMultiTaps[i] = Integer.parseInt(st.nextToken());
        }

        if (n == 0 || m == 0) {
            bw.write("1" + "\n");
        } else {
            Arrays.sort(firstMultiTaps, Collections.reverseOrder());
            Arrays.sort(secondMultiTaps, Collections.reverseOrder());

            int count = 0;
            int index = 0;

            for (int i = 0; i < firstMultiTaps.length; i++) {
                for (int j = 0; j < firstMultiTaps[i]; j++) {
                    if (index == secondMultiTaps.length) {
                        break;
                    }
                    count += secondMultiTaps[index];
                    index++;
                }
                if (index < secondMultiTaps.length && i + 1 < firstMultiTaps.length) {
                    count--;
                }
            }
            bw.write(String.valueOf(count));
        }

        br.close();
        bw.flush();
        bw.close();
    }

}
