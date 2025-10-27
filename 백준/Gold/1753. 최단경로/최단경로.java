import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1753번
    // 다익스트라
    // 간선에 음수가 없다 -> 최단 거리를 구할 수 있는 다익스트라 알고리즘을 활용 가능
    // 우선순위 큐를 활용해야 한다. 거리가 짧은 순서대로 방문할 것이기 때문.

    static int[] distance;
    static int V, E;
    static ArrayList<ArrayList<Edge>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        distance = new int[V];

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
            distance[i] = Integer.MAX_VALUE;
        }

        int start = Integer.parseInt(br.readLine()) - 1;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Edge(to, cost));
        }

        dijkstra(start);

        for (int dist : distance) {
            bw.write((dist == Integer.MAX_VALUE ? "INF" : dist) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        distance[start] = 0;
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            int currNode = curr.to;
            int currCost = curr.cost;

            // 이미 처리된 노드라면 스킵
            if (currCost > distance[currNode]) {
                continue;
            }

            // 현재 노드와 연결된 다른 노드들 확인
            for (Edge edge : graph.get(currNode)) {
                int nextNode = edge.to;
                int newCost = currCost + edge.cost;

                // 더 짧은 경로를 발견하면 갱신
                if (newCost < distance[nextNode]) {
                    distance[nextNode] = newCost;
                    pq.offer(new Edge(nextNode, newCost));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int to; // 목적지 노드
        int cost; // 비용 (가중치)

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
