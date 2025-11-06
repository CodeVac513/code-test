

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 11657번
    // 벨만 포드
    // 1. 엣지 리스트로 그래프를 구현한다.
    // 2. 최단 경로 배열을 초기화한다.
    // -> 벨만 포드는 엣지를 중심으로 동작하는 알고리즘이다.
    // 최단 경로 배열은 출발 노드가 0, 나머지는 무한대로 초기화한다.
    // 3. 모든 엣지를 확인하고 정답 배열 업데이트하기
    // -> 최단 경로 배열에서 업데이트 반복 횟수는 노드 개수 - 1번이다.
    // 노드가 N개이며 음수 사이클이 없는 경우,
    // 출발 노드와 도착 노드의 최단 거리에서 엣지는 N - 1개 이하로 거치게 된다.
    // 4. 음수 사이클 유무 확인하기
    // -> 모든 에지를 한 번씩 다시 사용해서, 업데이트되는 노드가 있다면 음수 사이클이 있는 것이다.

    // 코드로 표현하기 위한 수행 과정
    // 1. 모든 엣지와 관련된 정보를 가져온 뒤, 조건에 따라 배열의 값을 업데이트한다.
    // - 출발 노드가 방문한 적이 없는 노드(distance[start] == Integer.MAX_VALUE)일 때, 값을 업데이트하지 않는다.
    // - 출발 노드의 거리 배열값 + 엣지 가중치(cost) < 종료 노드의 거리 배열값일 때, 종료 노드의 거리 배열값(distance[end])를 업데이트한다.
    // 2. 노드 개수 - 1번 만큼, 위의 과정을 반복한다.
    // 3. 음수 사이클을 확인하기 위해 1번을 한 번 더 수행하고, 업데이트가 된다면 음수 사이클이 존재한다.

    static Edge[] edges;
    static long[] distance;

    static int N;
    static int M;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge[M];
        distance = new long[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(start, end, cost);
        }
        bellmanFord(1);

        bw.flush();
        bw.close();
        br.close();
    }

    static void bellmanFord(int startNode) {
        distance[startNode] = 0;

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Edge edge = edges[j];

                if (distance[edge.start] != Integer.MAX_VALUE
                        && distance[edge.end] > distance[edge.start] + edge.cost) {
                    distance[edge.end] = distance[edge.start] + edge.cost;
                }
            }
        }

        boolean isNegativeCycle = false;
        for (int i = 0; i < M; i++) {
            Edge edge = edges[i];

            if (distance[edge.start] != Integer.MAX_VALUE
                    && distance[edge.end] > distance[edge.start] + edge.cost) {
                isNegativeCycle = true;
            }
        }

        if (!isNegativeCycle) {
            for (int i = 2; i <= N; i++) {
                if (distance[i] == Integer.MAX_VALUE) {
                    System.out.println("-1");
                } else {
                    System.out.println(distance[i]);
                }
            }
        } else {
            System.out.println("-1");
        }

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
