import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 1717번
    // 유니온 파인드

    static int[] parent;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int operator = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            calculate(operator, a, b);
        }


        bw.flush();
        bw.close();
        br.close();
    }

    static void calculate(int type, int a, int b) {
        if (type == 1) {
            if (isSame(a, b)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            return;
        }

        if (type == 0) {
            union(a, b);
            return;
        }
    }


    static int find(int x) {
        if (x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }

    static boolean isSame(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return true;
        return false;
    }

}