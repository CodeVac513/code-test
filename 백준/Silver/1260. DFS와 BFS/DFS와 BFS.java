import java.util.*;
import java.lang.*;
import java.math.BigInteger;
import java.io.*;

public class Main {
    // 1260번
    // 완전 탐색
    // DFS와 BFS로 완전 탐색을 구현한 뒤, 그 과정을 출력하면 된다.
    // DFS는 재귀나 반복문으로 구현할 수 있다.
    // - 무조건 가장 가까운 노드를 다음 노드로 선정해서 진행한다.
    // - 그러면 하나의 리프 노드를 찾고 다시 처음 노드로 돌아오게 된다
    // - 루트 노드의 다음 자식 노드를 찾아서 리프까지 내려간다.
    // - 반복한다.
    // BFS는 큐를 활용해서 구할 수 있다.
    // - 첫 번째로 방문하는 시작 노드를 결정하고 저장한다.
    // - 큐에서 노드를 뽑고, 해당 노드와 연결되어 있는 노드를 모두 삽입한다.
    // - 큐에서 노드를 뽑고, 해당 노드와 연결되어 있는 노드를 또 삽입한다.
    // 위 과정을 반복하면 같은 레벨에 있는 트리 노드를 순서대로 탐색할 수 있다.

    // 인접한 노드를 표현하는데는 2가지 방법이 가능하다.
    // 인접 행렬과 인접 리스트
    // 둘 모두 풀이해보자.

    // 오답 노트)
    // 인접 리스트를 사용할 때는 삽입한 순서대로 데이터를 순회한다. 따라서 가중치가 있다거나 작은 수부터 방문해야 한다면 정렬을 꼭 해야 한다.

    static int[][] adjacentArray;
    static ArrayList<Integer>[] adjacentList;
    static boolean[] visited;
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int startNode = Integer.parseInt(st.nextToken());

        adjacentArray = new int[N][N];

        adjacentList = new ArrayList[N];
        for (int i = 0; i < adjacentList.length; i++) {
            adjacentList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            adjacentArray[start - 1][start - 1] = -1;
            adjacentArray[start - 1][end - 1] = 1;
            adjacentArray[end - 1][start - 1] = 1;

            adjacentList[start - 1].add(end - 1);
            adjacentList[end - 1].add(start - 1);
        }

        for (int i = 0; i < N; i++) {
            Collections.sort(adjacentList[i]);
        }

        visited = new boolean[N];
        dfsAdjacentList(startNode - 1);
        System.out.println();
        visited = new boolean[N];
        bfsAdjacentList(startNode - 1);

        br.close();
        bw.flush();
        bw.close();
    }

    public static void dfs(int startNode) {
        System.out.print((startNode + 1) + " ");
        visited[startNode] = true;
        for (int i = 0; i < adjacentArray[startNode].length; i++) {
            if (i != startNode && adjacentArray[startNode][i] == 1) {
                if (!visited[i]) {
                    dfs(i);
                }
            }
        }
    }

    public static void bfs(int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startNode);
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            if (visited[currentNode])
                continue;
            System.out.print((currentNode + 1) + " ");
            visited[currentNode] = true;
            for (int i = 0; i < adjacentArray[currentNode].length; i++) {
                if (!visited[i]) {
                    queue.offer(i);
                }
            }
        }
    }

    public static void dfsAdjacentList(int startNode) {
        System.out.print((startNode + 1) + " ");
        visited[startNode] = true;
        for (int child : adjacentList[startNode]) {
            if (!visited[child]) {
                dfs(child);
            }
        }
    }

    public static void bfsAdjacentList(int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startNode);
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            if (visited[currentNode])
                continue;
            System.out.print((currentNode + 1) + " ");
            visited[currentNode] = true;
            for (int child : adjacentList[currentNode]) {
                if (!visited[child]) {
                    queue.offer(child);
                }
            }
        }
    }
}
