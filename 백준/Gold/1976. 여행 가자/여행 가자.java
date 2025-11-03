import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1976번
    // 유니온 파인드
    // 유니온 파인드를 활용하면 모든 노드는 대표 노드를 가리키게 된다.
    // 그래서 E C B C D를 입력할 때, 모두 같은 대표 노드를 가리킨다면 서로 이어져 있음을 알 수 있음.
    // 근데 N이 작아서 플로이드 워셜로도 풀 수 있을 것 같은데? 두 가지 다 도전해보자.

    static int[] parent;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int iter = st.countTokens();
            for (int j = 1; j <= iter; j++) {
                int status = Integer.parseInt(st.nextToken());
                if (status == 1) {
                    union(i, j);
                }
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());


        int start = Integer.parseInt(st.nextToken());
        int root = find(start);
        int iter = st.countTokens();
        boolean isPossible = true;
        for (int i = 0; i < iter; i++) {
            int currentNode = Integer.parseInt(st.nextToken());
            int currentRoot = find(currentNode);

            if (currentRoot != root) {
                isPossible = false;
                break;
            }
        }

        if (isPossible) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }


        bw.flush();
        bw.close();
        br.close();
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }

    static int find(int a) {
        if (parent[a] == a)
            return a;

        return parent[a] = find(parent[a]);
    }
}

// import java.io.*;
// import java.util.*;
// import java.lang.*;

// public class Main {
// // 1976번
// // 유니온 파인드
// // 유니온 파인드를 활용하면 모든 노드는 대표 노드를 가리키게 된다.
// // 그래서 E C B C D를 입력할 때, 모두 같은 대표 노드를 가리킨다면 서로 이어져 있음을 알 수 있음.
// // 근데 N이 작아서 플로이드 워셜로도 풀 수 있을 것 같은데? 두 가지 다 도전해보자.

// static int[][] distance;
// static int n;
// static int m;

// public static void main(String[] args) throws IOException {
// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
// n = Integer.parseInt(br.readLine());
// m = Integer.parseInt(br.readLine());

// final int INF = 1000 * 200 + 1;

// distance = new int[n][n];

// for (int i = 0; i < n; i++) {
// Arrays.fill(distance[i], INF);
// distance[i][i] = 0;
// }

// for (int i = 0; i < n; i++) {
// StringTokenizer st = new StringTokenizer(br.readLine());
// for (int j = 0; j < n; j++) {
// int status = Integer.parseInt(st.nextToken());
// if (status == 1) {
// distance[i][j] = 1;
// distance[j][i] = 1;
// }
// }
// }

// dp();

// StringTokenizer st = new StringTokenizer(br.readLine());
// int current = Integer.parseInt(st.nextToken()) - 1;
// int iter = st.countTokens();
// boolean flag = true;
// for (int i = 0; i < iter; i++) {
// int next = Integer.parseInt(st.nextToken()) - 1;

// if (distance[current][next] < INF) {
// current = next;
// } else {
// flag = false;
// break;
// }
// }

// if (flag) {
// bw.write("YES");
// } else {
// bw.write("NO");
// }

// bw.flush();
// bw.close();
// br.close();
// }

// static void dp() {
// for (int k = 0; k < n; k++) {
// for (int i = 0; i < n; i++) {
// for (int j = 0; j < n; j++) {
// if (distance[i][j] > distance[i][k] + distance[k][j]) {
// distance[i][j] = distance[i][k] + distance[k][j];
// }
// }
// }
// }
// }

// }

