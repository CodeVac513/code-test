import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 24174번
    // 구현, 힙에 대한 개념 학습
    // 최소 힙은 루트가 가장 작은 값으로 자식이 부모보다 큰 값을 가지도록 정렬하는 자료구조다.
    // 힙의 개념을 완전 이진 트리로 나타내는데, 물리적으로 구현할 때는 배열을 사용하는 경우가 많다.
    // 루트부터 1, 좌우의 자식은 2,3... 이런 식으로 나타내면 배열의 index를 활용해서도 나타낼 수 있다.
    // 배열에서 어떤 노드 k의 좌우 자식의 index는 2 * k(left), 2 * k + 1 (right)이다.
    // 오답 노트 1)
    // count를 추가하는 로직이 swap에 있어야 한다. 그리고 count == K일 때, 모든 동작을 종료시키도록 만들어야 함.

    static int count = 0;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] nums = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        heapSort(nums, N);

        if (count < K) {
            bw.write(String.valueOf(-1));
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= N; i++) {
                sb.append(nums[i]).append(" ");
            }

            bw.write(sb.toString());
        }

        br.close();
        bw.flush();
        bw.close();
    }

    public static void heapSort(int[] A, int N) {
        buildMinHeap(A, N);
        if (count == K) {
            return;
        }
        for (int i = N; i >= 2; i--) {
            swap(A, 1, i);
            heapify(A, 1, i - 1);
            if (count == K) {
                return;
            }
        }
    }

    public static void buildMinHeap(int[] A, int N) {
        for (int i = N / 2; i >= 1; i--) {
            heapify(A, i, N);
            if (count == K) {
                return;
            }
        }
    }

    public static void heapify(int[] A, int k, int N) {
        int left = 2 * k;
        int right = 2 * k + 1;
        int smaller;

        if (count == K) {
            return;
        }
        
        if (right <= N) {
            if (A[left] < A[right]) {
                smaller = left;
            } else {
                smaller = right;
            }
        } else if (left <= N) {
            smaller = left;
        } else {
            return;
        }

        if (A[smaller] < A[k]) {
            swap(A, k, smaller);
            heapify(A, smaller, N);
        }
    }

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
        count++;
    }
}
