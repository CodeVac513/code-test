import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    // 1504번
    // dijkstra
    // 특정한 정점 2개를 통과해야 하는 다익스트라?
    // 다익스트라를 3번 돌려서
    // 1 -> x -> y -> N / 1 -> y -> x -> N 중 작은 값을 선택하면 되지 않을까?

    static int N;
    static int E;
    static ArrayList<Edge>[] graph;
    static int[] distance1;
    static int[] distance2;
    static int[] distance3;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N];
        distance1 = new int[N];
        distance2 = new int[N];
        distance3 = new int[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
            distance1[i] = Integer.MAX_VALUE;
            distance2[i] = Integer.MAX_VALUE;
            distance3[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        int essentialFirstPoint = Integer.parseInt(st.nextToken()) - 1;
        int essentialSecondPoint = Integer.parseInt(st.nextToken()) - 1;
        dijkstra(0, distance1);
        dijkstra(essentialFirstPoint, distance2);
        dijkstra(essentialSecondPoint, distance3);

        long firstDistance = Long.MAX_VALUE;
        long secondDistance = Long.MAX_VALUE;

        // 1 -> x -> y -> N
        if (distance1[essentialFirstPoint] != Integer.MAX_VALUE
                && distance2[essentialSecondPoint] != Integer.MAX_VALUE
                && distance3[N - 1] != Integer.MAX_VALUE) {
            firstDistance = (long) distance1[essentialFirstPoint] + distance2[essentialSecondPoint] + distance3[N - 1];
        }

        // 1 -> y -> x -> N
        if (distance1[essentialSecondPoint] != Integer.MAX_VALUE
                && distance3[essentialFirstPoint] != Integer.MAX_VALUE
                && distance2[N - 1] != Integer.MAX_VALUE) {
            secondDistance = (long) distance1[essentialSecondPoint] + distance3[essentialFirstPoint] + distance2[N - 1];
        }

        long result = Math.min(firstDistance, secondDistance);
        if (result >= Long.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(Long.toString(result));
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static void dijkstra(int startPoint, int[] distance) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        distance[startPoint] = 0;
        pq.add(new Edge(startPoint, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentNode = current.to;
            int currentCost = current.cost;

            if (distance[currentNode] < currentCost) continue;

            for (Edge next : graph[currentNode]) {
                int nextNode = next.to;
                int nextCost = next.cost;

                if (distance[nextNode] > distance[currentNode] + nextCost) {
                    distance[nextNode] = distance[currentNode] + nextCost;
                    pq.add(new Edge(nextNode, distance[nextNode]));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }


}
