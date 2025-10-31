import java.util.*;

class Solution {
    // 다익스트라를 3번 돌릴 수 있지 않을까?
    // Start -> 임의의 정점 X, X -> A 목적지, X -> B 목적지
    // A와 B 따로 가기
    // 이 3가지 경우의 수를 탐색하려면 3번의 다익스트라를 적용해야 함.
    
    ArrayList<Edge>[] graph;
    int[] distance;
    int n;
    int s,a,b;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        this.n = n;
        this.a = a - 1;
        this.b = b - 1;
        this.s = s - 1;
        distance = new int[n];
        final int INF = 200 * 100000 + 1;

        graph = new ArrayList[n];
        for(int i = 0 ; i < n ; i++) {
            graph[i] = new ArrayList();
        }
        
        for(int i = 0 ; i < fares.length ; i++) {
            int[] info = fares[i];
            int from = info[0] - 1;
            int to = info[1] - 1;
            int cost = info[2];
            
            graph[from].add(new Edge(to, cost));
            graph[to].add(new Edge(from, cost));
        }
        
        // S에서 모든 노드까지의 거리
        Arrays.fill(distance, INF);
        dijkstra(this.s);
        int[] distFromS = distance.clone();  
        
        // A에서 모든 노드까지의 거리
        Arrays.fill(distance, INF);
        dijkstra(this.a);
        int[] distFromA = distance.clone();
        
        // B에서 모든 노드까지의 거리
        Arrays.fill(distance, INF);
        dijkstra(this.b);
        int[] distFromB = distance.clone();

        int answer = INF;
        for(int via = 0; via < n; via++) {
            if(distFromS[via] != INF && distFromA[via] != INF && distFromB[via] != INF) {
                int cost = distFromS[via] + distFromA[via] + distFromB[via];
                answer = Math.min(answer, cost);
            }
        }
        return answer;
    }
    
    public void dijkstra(int startPoint) {
        PriorityQueue<Edge> pq = new PriorityQueue();
        pq.offer(new Edge(startPoint, 0));
        distance[startPoint] = 0;
        
        while(!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentNode = current.to;
            int currentCost = current.cost;
            
            if(currentCost > distance[currentNode]) continue;
            
            for(int i = 0 ; i < graph[currentNode].size() ; i++) {
                Edge next = graph[currentNode].get(i);
                int nextNode = next.to;
                int nextCost = next.cost;
                
                if(distance[nextNode] > distance[currentNode] + nextCost) {
                    distance[nextNode] = distance[currentNode] + nextCost;
                    pq.offer(new Edge(nextNode, distance[nextNode]));
                }
            }
        }
    }
    
    class Edge implements Comparable<Edge> {
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