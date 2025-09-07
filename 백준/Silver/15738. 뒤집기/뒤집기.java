import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 15738번
    // 구현, 시뮬레이션?
    // Node 클래스를 구현해서 정수 i와 초기 위치를 저장하자.
    // 그리고 규칙에 따라서 원소들을 이동시킨 뒤 초기 위치와 현재 인덱스를 비교하면 될 듯?
    // 단 K값은 배열의 index와 다르게 1부터 카운트함 -> 나중에 index + 1 필요

    // 오답 노트 1)
    // subList는 새로운 콜렉션을 가져오는 게 아니라 기존 객체의 메모리를 참조함. 따라서 원본이 수정되면 subList 또한 같이 수정됨.
    // 오답 노트 2)
    // 시간 초과 발생 -> 10^5개의 N에서 현재 풀이로 최악의 경우에는 10^5를 연산한다. 그래서 O(N^2)으로는 풀 수 없다.
    // 그러면 k번째 숫자만 고려하자. 문제 규칙대로 k번째 숫자가 어디로 옮겨지는지만 확인하면 된다.
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int currentIndex = Integer.parseInt(br.readLine());

            if (currentIndex > 0 && currentIndex < K) {
                continue;
            }
            if (currentIndex < 0 && N + currentIndex + 1 > K) {
                continue;
            }

            if (currentIndex > 0) {
                K = currentIndex - K + 1;
            } else {
                K = 2 * N - K + currentIndex + 1;
            }
        }

        bw.write(String.valueOf(K));
        br.close();
        bw.flush();
        bw.close();
    }

//    public static int getStartIndex(int calculation, ArrayList<Node> nums) {
//        int startIndex = 0;
//        if (calculation < 0) {
//            startIndex = nums.size() + calculation;
//        }
//        return startIndex;
//    }
//
//    public static int getEndIndex(int calculation, ArrayList<Node> nums) {
//        int endIndex = calculation - 1;
//        if (calculation < 0) {
//            endIndex = nums.size() - 1;
//        }
//
//        return endIndex;
//    }
//
//    public static List<Node> getSubListFromArrayListWithIndex(int calculation, ArrayList<Node> nums,
//                                                              int startIndex, int endIndex) {
//        List<Node> result = new ArrayList<>(nums.subList(startIndex, endIndex + 1));
//        Collections.reverse(result);
//        return result;
//    }
//
//    static class Node {
//        int num;
//        int initialIndex;
//
//        Node(int num, int initialIndex) {
//            this.num = num;
//            this.initialIndex = initialIndex;
//        }
//    }
}
