import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 2606
    // DFS, BFS
    // 바이러스가 걸린 컴퓨터와 연결된 컴퓨터는 모두 감염된다.
    // 따라서 완전 탐색을 통해 카운트를 세면 그게 정답이다.
    // (최초의 컴퓨터는 이미 감염되어 있으므로 카운트하지 않는다.
    // 그러면 이 그래프를 어떻게 나타낼 지 생각해야 한다.
    // 1. 인접 리스트
    // 2. 인접 행렬
    // 3. 엣지 리스트
    // -> 이 문제는 엣지의 개수가 적으므로, 인접 리스트로 작성하면 될 것 같다.

    static boolean[] visited;
    static List<List<Integer>> graph;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());

            graph.get(startNode - 1).add(endNode - 1);
            graph.get(endNode - 1).add(startNode - 1);
        }

        dfs(0);
        bw.write(String.valueOf(answer - 1));

        br.close();
        bw.flush();
        bw.close();
    }

    static void dfs(int startNode) {
        if (visited[startNode]) return;

        visited[startNode] = true;
        answer++;
        List<Integer> connectedNodeList = graph.get(startNode);
        for (int nextNode : connectedNodeList) {
            dfs(nextNode);
        }
    }
}
