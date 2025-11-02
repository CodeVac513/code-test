import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 9370번
    // 다익스트라
    // 특정 엣지를 지나야 한다는 조건은 특정 노드 2개를 지나야 한다는 의미와 같다.
    // start -> x -> y -> end
    // 여기서 x -> y 사이의 엣지는 문제에서 주어진 엣지를 더하면 된다.
    // 즉, start -> x, y -> end의 최소 거리를 구하면 된다.
    // 이 때, start -> y -> x -> end일 수도 있다.

    static int[] distance;
    static int n;
    static int m;
    static int t;
    static ArrayList<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        final int INF = 1000 * 2000 + 1;
        for (int tc = 0; tc < T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            // init
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            distance = new int[n + 1];
            graph = new ArrayList[n + 1];
            for (int i = 0; i < n + 1; i++) {
                graph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            // s->g, g->h, h-> candidate 후보군
            // 혹은 s->h, h->g, g->candidate
            int gh_cost = 0;
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                graph[a].add(new Edge(b, d));
                graph[b].add(new Edge(a, d));

                if ((a == g && b == h) || (a == h && b == g)) {
                    gh_cost = d;
                }
            }
            ArrayList<Integer> candidate = new ArrayList<>();
            for (int i = 0; i < t; i++) {
                candidate.add(Integer.parseInt(br.readLine()));
            }

            Arrays.fill(distance, INF);
            dijkstra(s);
            int[] distanceFromStart = distance.clone();

            Arrays.fill(distance, INF);
            dijkstra(g);
            int[] distanceFromG = distance.clone();

            Arrays.fill(distance, INF);
            dijkstra(h);
            int[] distanceFromH = distance.clone();

            ArrayList<Integer> answer = new ArrayList<>();
            for (int destination : candidate) {
                int originalDistance = distanceFromStart[destination];

                // s -> g -> h -> destination
                int path1 = distanceFromStart[g] + gh_cost + distanceFromH[destination];

                // s -> h -> g -> destination
                int path2 = distanceFromStart[h] + gh_cost + distanceFromG[destination];

                int minLengthPath = Math.min(path1, path2);

                if (minLengthPath == originalDistance) {
                    answer.add(destination);
                }
            }

            Collections.sort(answer);
            for (int destination : answer) {
                bw.write(destination + " ");
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void dijkstra(int startNode) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        distance[startNode] = 0;
        pq.offer(new Edge(startNode, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentIndex = current.to;
            int currentCost = current.cost;

            if (currentCost > distance[currentIndex])
                continue;

            for (Edge next : graph[currentIndex]) {
                int nextIndex = next.to;
                int nextCost = next.cost;

                if (distance[nextIndex] > distance[currentIndex] + nextCost) {
                    distance[nextIndex] = distance[currentIndex] + nextCost;
                    pq.offer(new Edge(nextIndex, distance[nextIndex]));
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
