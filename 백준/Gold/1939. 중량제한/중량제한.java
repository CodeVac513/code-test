import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1939번
    // 파라메트릭 서치
    // 다리마다 중량 제한이 있음.
    // 그래서 N개의 섬 사이를 다니면서, 한 번의 이동으로 옮길 수 있는 중량의 최댓값을 구해야 함.
    // 최적화 문제를 결정 문제로.
    // isPossible을 검증하는 방법
    // mid 이상의 중량 제한을 가진 다리를 탐색해야 함.
    // union find로 start와 end가 연결되어있는지 확인 하는 방법과 BFS로 모든 섬을 탐색하는 방법, 2가지가 있음.

    static int N;
    static int M;
    static ArrayList<Edge>[] graph;
    static int start;
    static int end;
    static long answer = 0;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];  // 1번~N번 노드
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[s].add(new Edge(s, e, w));
            graph[e].add(new Edge(e, s, w));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        binarySearch();
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    static void binarySearch() {
        long left = 0;
        long right = 1_000_000_000L;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (isPossibleUnionFind(mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    static boolean isPossibleBFS(long mid) {
        // mid 이상인 다리만 선택하고, 그 다리들로 X -> Y로 도달가능한가?
        // BFS

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int current = q.poll();

            if (current == end) return true;

            for (Edge e : graph[current]) {
                if (e.weight >= mid && !visited[e.end]) {
                    visited[e.end] = true;
                    q.offer(e.end);
                }
            }
        }
        return false;
    }

    static boolean isPossibleUnionFind(long mid) {
        // mid 이상인 다리만 선택하고, 그 다리들로 X -> Y로 도달가능한가?
        // Union Find

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            for (Edge e : graph[i]) {
                if (e.weight >= mid) {
                    union(e.start, e.end);
                }
            }
        }

        return find(start) == find(end);
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }

    static class Edge {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}