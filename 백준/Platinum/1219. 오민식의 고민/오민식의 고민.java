

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1219번
    // 벨만 포드
    // 이 문제에서는 두 가지 상태를 고려해야 한다.
    // 1. 도착지가 출발지와 연결되지 않는 경우
    // 2. 양수 사이클이 발생하여 돈을 무한대로 복사할 수 있는 경우
    // 3. 일반적인 경우
    // 1번의 경우에는 모든 노드를 Long.MIN_VALUE로 초기화하여 도달할 수 없음을 확인한다.
    // 2번의 경우, 양수 사이클이 발생할 때 Long.MAX_VALUE로 도착 노드를 업데이트한다.
    // 3번의 경우에는 그냥 계산해놓은 값을 사용하면 된다.

    static long[] money;
    static int[] salesMoney;
    static Edge[] edges;
    static int N;
    static int M;
    static int startNode;
    static int endNode;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        startNode = Integer.parseInt(st.nextToken());
        endNode = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        salesMoney = new int[N];
        money = new long[N];
        edges = new Edge[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(start, end, -cost);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            salesMoney[i] = Integer.parseInt(st.nextToken());
        }

        bellmanFord(startNode, endNode);

        bw.flush();
        bw.close();
        br.close();
    }

    static void bellmanFord(int startNode, int endNode) {
        Arrays.fill(money, Long.MIN_VALUE);
        money[startNode] = salesMoney[startNode];
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M; j++) {
                Edge edge = edges[j];

                if (money[edge.start] == Long.MIN_VALUE)
                    continue;
                if (money[edge.start] == Long.MAX_VALUE) {
                    money[edge.end] = Long.MAX_VALUE;
                } else if (money[edge.end] < money[edge.start] + salesMoney[edge.end] + edge.cost) {
                    money[edge.end] = money[edge.start] + salesMoney[edge.end] + edge.cost;
                }
            }
        }

        // 양수 사이클 감지 및 전파
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Edge edge = edges[j];

                if (money[edge.start] == Long.MIN_VALUE)
                    continue;
                if (money[edge.start] == Long.MAX_VALUE) {
                    money[edge.end] = Long.MAX_VALUE;
                } else if (money[edge.end] < money[edge.start] + salesMoney[edge.end] + edge.cost) {
                    // 양수 사이클 발견하면 업데이트
                    money[edge.end] = Long.MAX_VALUE;
                }
            }
        }


        if (money[endNode] == Long.MIN_VALUE) {
            System.out.println("gg");
        } else if (money[endNode] == Long.MAX_VALUE) {
            System.out.println("Gee");
        } else {
            System.out.println(money[endNode]);
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
