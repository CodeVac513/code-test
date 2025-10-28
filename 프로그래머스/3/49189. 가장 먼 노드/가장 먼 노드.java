import java.util.*;

class Solution {
    // 가중치가 1인 다익스트라 문제
    
    int n;
    ArrayList<Edge>[] graph;
    Integer[] distance;
    
    public int solution(int n, int[][] edge) {
        //init
        this.n = n;
        distance = new Integer[n];
        graph = new ArrayList[n];
        
        for(int i = 0 ; i < n ; i++) {
            graph[i] = new ArrayList();
            distance[i] = Integer.MAX_VALUE;
        }
        
        for(int i = 0 ; i < edge.length ; i++) {
            int[] info = edge[i];
            int from = info[0] - 1;
            int to = info[1] - 1;
            
            graph[from].add(new Edge(to, 1));
            graph[to].add(new Edge(from, 1));
        }
        
        dijkstra(0);
        Arrays.sort(distance, (a,b) -> b - a);
        int biggest = distance[0];
        int answer = 1;
        for(int i = 1 ; i < n ; i++) {
            if(distance[i] == biggest) answer++;
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