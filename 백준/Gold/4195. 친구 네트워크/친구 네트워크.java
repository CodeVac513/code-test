

import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {
    // 4195번
    // 유니온 파인드
    // 이름을 어떻게 관리할 지만 생각하면 되는 문제.
    // int[]의 parent 배열과 String[]의 name 배열을 가지고 있다면?...
    // 오답노트) parent 배열의 크기는 F * 2 + 1이 되어야 함. F가 친구 관계의 갯수이므로,
    // 최대 F * 2명의 사람이 나타날 수 있음.

    static int[] parent;
    static int[] size;
    static Map<String, Integer> nameMap;
    static int F;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            F = Integer.parseInt(br.readLine());
            parent = new int[F * 2 + 1];
            size = new int[F * 2 + 1];
            nameMap = new HashMap<>();

            for (int i = 0; i < F * 2 + 1; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            int parentIndex = 1;
            HashSet<String> checkNewNameSet = new HashSet<>();

            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                if (checkNewNameSet.add(a)) {
                    nameMap.put(a, parentIndex);
                    parentIndex++;
                }

                if (checkNewNameSet.add(b)) {
                    nameMap.put(b, parentIndex);
                    parentIndex++;
                }

                union(nameMap.get(a), nameMap.get(b));
                int root = find(nameMap.get(a));

                bw.write(size[root] + "\n");
            }


        }
        bw.flush();
        bw.close();
        br.close();
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
            size[a] += size[b];
        }
    }

    static int find(int a) {
        if (a == parent[a])
            return a;

        return parent[a] = find(parent[a]);
    }
}
