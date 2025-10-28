import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 5972번
    // dijkstra
    // 다익스트라에서 중요한 것은 다음과 같다.
    // 1. 시작 지점을 제외하고 무한대로 초기화한 distance 배열
    // 2. Vertex와 가중치를 가지는 Edge를 저장하는 인접 리스트 graph
    // 3. 최단 경로만 방문하는 Priority Queue
    // 4. 가중치와 다음 Vertex index를 가지고 있는 Edge 클래스

    static int N;
    static int M;
    static ArrayList<ArrayList<Edge>> graph;
    static boolean[] visited;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        distance = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, weight));
            graph.get(to).add(new Edge(from, weight));
        }

        dijkstra(1);
        bw.write(String.valueOf(distance[N]));

        bw.flush();
        bw.close();
        br.close();
    }

    static void dijkstra(int startPoint) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(startPoint, 0));
        distance[startPoint] = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentVertex = current.to;

            if (visited[currentVertex]) {
                continue;
            }

            visited[currentVertex] = true;
            for (int i = 0; i < graph.get(currentVertex).size(); i++) {
                Edge temp = graph.get(currentVertex).get(i);
                int next = temp.to;
                int weight = temp.cost;

                if (distance[next] > distance[currentVertex] + weight) {
                    distance[next] = distance[currentVertex] + weight;
                    pq.add(new Edge(next, distance[next]));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }

}
