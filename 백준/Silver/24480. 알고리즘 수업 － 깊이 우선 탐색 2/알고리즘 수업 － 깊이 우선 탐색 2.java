import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    // 24480번
    // DFS, 깊이 우선 탐색
    // 말 그대로 깊이 우선 탐색을 진행하면서 방문하는 노드를 기억하고, 노드의 순서(오름차순)대로 방문한 순서를 출력한다. 방문할 수 없는 경우에는 0을 출력한다.
    // 전역적으로 사용하는 배열을 만든 뒤, 0으로 초기화를 한다.
    // 그리고 방문하는 순서는 DFS 메서드를 만들고 인자로 count를 받게하여 저장하면 될 것이다.
    // 여러 개의 노드가 연결된 경우, 내림차순으로 방문하도록 인접노드가 모두 저장되면 정렬을 해야 한다.
    // 오답노트)
    // count를 입력 인자로 관리하는 게 아니라 전역 변수로 관리해야 한다. 여러 개가 연결되어 있는 경우, 같은 count로 계산되기 때문!
    static Boolean[] visited;
    static Node[] nodeArray;
    static int[] nodeOrder;
    static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        nodeArray = new Node[N + 1];
        visited = new Boolean[N + 1];
        nodeOrder = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            nodeArray[i] = new Node(i);
            visited[i] = false;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int currentIndex = Integer.parseInt(st.nextToken());
            int nextIndex = Integer.parseInt(st.nextToken());
            Node currentNode = nodeArray[currentIndex];
            Node nextNode = nodeArray[nextIndex];
            currentNode.addNode(nextNode);
            nextNode.addNode(currentNode);
        }

        for (Node node : nodeArray) {
            if (node == null) {
                // 0번 노드는 없으므로 건너뛰기
                continue;
            }
            node.sortNodes();
        }

        dfs(R);
        for (int i = 1; i <= N; i++) {
            bw.write(nodeOrder[i] + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    static void dfs(int start) {
        if (visited[start]) {
            return;
        }

        visited[start] = true;
        nodeOrder[start] = count;
        count++;
        Node currentNode = nodeArray[start];
        ArrayList<Node> adjacentNodes = currentNode.getNodes();

        for (Node node : adjacentNodes) {
            dfs(node.getNum());
        }
    }

    static class Node {
        int num;
        ArrayList<Node> nodes;

        Node(int num) {
            this.num = num;
            nodes = new ArrayList<>();
        }

        int getNum() {
            return num;
        }

        void addNode(Node node) {
            nodes.add(node);

        }

        ArrayList<Node> getNodes() {
            return nodes;
        }

        void sortNodes() {
            Collections.sort(nodes, (o1, o2) -> {
                return o2.getNum() - o1.getNum();
            });
        }
    }
}