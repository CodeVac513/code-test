

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1865번
    // 벨만 포드
    // 시간이 되돌아가는 경우 -> 음수 사이클이 있는지 확인하라는 의미
    // 도로는 방향이 있으며, 웜홀은 방향이 없다. -> 도로는 양방향 엣지, 웜홀은 단방향 엣지
    // 웜홀은 cost가 음수, 도로는 양수임.
    // 오답 노트)
    // 처음에는 모든 노드를 상대로 벨만 포드를 실행하려고 했는데, 시간 초과 발생
    // distance 배열 자체를 0으로 초기화해서 음수 노드를 확인할 수도 있음.
    // 이렇게 동작할 수 있는 이유: 지금 음수 사이클은 업데이트가 계속 일어나는지만 확인하면 됨.
    // 절대적인 거리의 값이 중요한 것이 아님.
    static ArrayList<Edge> edges;
    static long[] distance;
    static int N;
    static int M;
    static int W;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int TC = Integer.parseInt(br.readLine());
        for (int t = 0; t < TC; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            distance = new long[N + 1];
            edges = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                edges.add(new Edge(start, end, cost));
                edges.add(new Edge(end, start, cost));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges.add(new Edge(start, end, -cost));
            }

            if (bellmanFord()) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean bellmanFord() {
        Arrays.fill(distance, 0);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 2 * M + W; j++) {
                Edge currentEdge = edges.get(j);

                if (distance[currentEdge.end] > distance[currentEdge.start] + currentEdge.cost) {
                    distance[currentEdge.end] = distance[currentEdge.start] + currentEdge.cost;
                }
            }
        }

        boolean isNegativeCycle = false;
        for (int j = 0; j < 2 * M + W; j++) {
            Edge currentEdge = edges.get(j);

            if (distance[currentEdge.end] > distance[currentEdge.start] + currentEdge.cost) {
                isNegativeCycle = true;
            }
        }

        return isNegativeCycle;
    }

    static class Edge {
        int start, end, cost;

        Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
