import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1717번
    // 유니온 파인드
    // 유니온 파인드 개념)
    // 유니온 파인드는 union 연산 + find 연산으로 구성된 알고리즘임.
    // union은 합집합 연산, find는 특정 원소 a를 찾았을 때 그 대표 집합 A의 대표 노드을 반환하는 연산
    // find의 예시)
    // a와 b와 c가 A라는 집합에 속하고 c가 A 집합의 대표일 때, f(a) = c, f(b) = c => a, b를 통해서 집합 A의 대표 노드 c를 반환함.
    // 유니온 파인드 원리)
    // 1. 1차원 배열을 선언, 각 노드가 연결되어 있지 않으므로 각 노드가 대표 노드가 됨.
    // 1번 노드 -> 1번 노드, 2번 노드 -> 2번 노드 ...
    // 2. 2개의 노드를 선택해서 각각의 대표 노드를 찾아 연결하는 union 연산을 시작함.
    // union(1,4) => 1번 노드 -> 1번 노드, 4번 노드 -> 1번 노드
    // union(5,6) => 5번 노드 -> 5번 노드, 6번 노드 -> 5번 노드
    // union(4,6) => 4번 노드 -> 1번 노드, 6번 노드 -> 5번 노드 -> 1번 노드,
    // 결과적으로 6번 노드가 4번 노드의 대표인 1번을 찾아서 자신의 대표 노드인 5번의 대표 노드를 1번으로 수정함.
    // 3. 대표 노드를 찾는 find 연산 시작
    // 3-1. 대상 노드 배열에 index와 value가 동일한 지 확인한다.
    // 3-2. 동일하지 않으면 value가 가리키는 index로 이동한다.
    // 3-3. 이동 위치의 index와 value가 같아질 때까지 3-1과 3-2를 반복한다. (재귀 가능)
    // 3-4. 대표 노드에 도달하면 재귀 함수를 빠져나오면서, 거치는 모든 노드의 value를 대표 노드 index값으로 업데이트한다.

    static int[] parent;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }

        Queue<int[]> query = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            int[] input = new int[3];
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                input[j] = Integer.parseInt(st.nextToken());
            }

            query.offer(input);
        }

        while (!query.isEmpty()) {
            int[] input = query.poll();
            calculate(input[0], input[1], input[2]);
        }


        bw.flush();
        bw.close();
        br.close();
    }

    static void calculate(int type, int a, int b) {
        if (type == 1) {
            if (isSame(a, b)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            return;
        }

        if (type == 0) {
            union(a, b);
            return;
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }

    }

    static int find(int a) {
        if (a == parent[a]) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }

    static boolean isSame(int a, int b) {
        return find(a) == find(b);
    }
}
