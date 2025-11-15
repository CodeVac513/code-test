import java.util.*;

class Solution {
    
    ArrayList<Edge>[] graph;
    int N;
    int K;
    int[] distance;
    final int INF = 50 * 10000 + 1;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        this.N = N;
        this.K = K;
        graph = new ArrayList[N];
        
        distance = new int[N];
        Arrays.fill(distance, INF);
        for(int i = 0 ; i < N ; i++) {
            graph[i] = new ArrayList();
        }
        
        for(int i = 0 ; i < road.length ; i++) {
            int[] info = road[i];
            int from = info[0] - 1;
            int to = info[1] - 1;
            int cost = info[2];
            
            graph[from].add(new Edge(to, cost));
            graph[to].add(new Edge(from, cost));
        }
        
        daijkstra(0);
        
        for(int i = 0 ; i < N ; i++) {
            if(distance[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
    
    public void daijkstra(int startPoint) {
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