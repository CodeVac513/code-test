import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1916번
    // dijkstra
    // 도시 비용은 0이상 이다 -> 다익스트라를 사용하기에 적절하다.
    // 다익스트라를 사용하기 위한 템플릿은 다음과 같다.
    // 1. graph(인접 리스트)
    // 2. priority queue (가장 짧은 곳을 먼저 방문하기 위한)
    // 3. distance(시작 지점에서 index번째 Vortex까지의 가중치 합-최단거리-을 저장)
    // 4. Edge 클래스(다음 노드 번호와 가중치가 저장되어 있고, 가중치 만으로 크기를 비교할 수 있는 클래스)
    // 5. Node 클래스
    // - 현재 처리할 노드 번호와 시작점부터의 누적 거리를 저장
    // - PriorityQueue에 저장되어, 비용 기준으로 정렬됨
    // - Comparable 구현으로 비용이 작은 순서대로 자동 정렬
    static int N;
    static int M;
    static int[] distance;
    static ArrayList<ArrayList<Edge>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        distance = new int[N];
        graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, weight));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int startIndex = Integer.parseInt(st.nextToken()) - 1;
        int endIndex = Integer.parseInt(st.nextToken()) - 1;
        dijkstra(startIndex);

        bw.write(String.valueOf(distance[endIndex]));

        bw.flush();
        bw.close();
        br.close();
    }

    static void dijkstra(int startNodeIndex) {
        distance[startNodeIndex] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(startNodeIndex, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int currentNodeIndex = currentNode.nodeId;
            int currentDistance = currentNode.distFromStart;

            if (currentDistance > distance[currentNodeIndex])
                continue;

            for (Edge edge : graph.get(currentNodeIndex)) {
                int nextNodeIndex = edge.to;
                int edgeCost = edge.cost;
                int newDistance = currentDistance + edgeCost;

                if (newDistance < distance[nextNodeIndex]) {
                    distance[nextNodeIndex] = newDistance;
                    pq.offer(new Node(nextNodeIndex, newDistance));
                }
            }
        }
    }

    static class Edge {
        int to; // 다음 Node의 index
        int cost; // 가중치

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    static class Node implements Comparable<Node> {
        int nodeId;
        int distFromStart;

        Node(int nodeId, int distFromStart) {
            this.nodeId = nodeId;
            this.distFromStart = distFromStart;
        }

        @Override
        public int compareTo(Node o) {
            return this.distFromStart - o.distFromStart;
        }
    }

}
