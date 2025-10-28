import java.util.*;

class Solution {
    // dijkstra
    // 1번에서 출발하고, 전체 마을에 대한 최소 경로를 구한다.
    // 그 후에 K 시간 이하인 경우만 반환
    
    ArrayList<Edge>[] graph;
    int[] distance;
    int N;
    int K;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        //init
        this.N = N;
        this.K = K;
        graph = new ArrayList[N];
        distance = new int[N];
        
        for(int i = 0 ; i < N ; i++) {
            graph[i] = new ArrayList();
            distance[i] = Integer.MAX_VALUE;
        }
        
        for(int i = 0 ; i < road.length ; i++) {
            int[] info = road[i];
            int from = info[0] - 1;
            int to = info[1] - 1;
            int cost = info[2];
            
            graph[from].add(new Edge(to, cost));
            graph[to].add(new Edge(from, cost));
        }
        
        dijkstra(0);
        
        for(int i = 0 ; i < N ; i++) {
            if(distance[i] <= K) answer++;
        }
        
        return answer;
    }
    
    void dijkstra(int startPoint) {
        PriorityQueue<Edge> pq = new PriorityQueue();
        distance[startPoint] = 0;
        pq.offer(new Edge(startPoint, 0));
        
        while(!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentNode = current.to;
            int currentCost = current.cost;
            
            if(distance[currentNode] < currentCost) continue;
            
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