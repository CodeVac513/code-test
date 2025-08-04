import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> adjacentList = new ArrayList<>();
        List<List<Integer>> invertedAdjacentList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            adjacentList.add(new ArrayList<>());
            invertedAdjacentList.add(new ArrayList<>());
        }

        Set<String> edgeSet = new HashSet<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adjacentList.get(u).add(v);
            invertedAdjacentList.get(v).add(u);
            edgeSet.add(u + " - " + v);
        }

        int count = 0;

        for (int z = 1; z <= N; z++) {
            List<Integer> parents = invertedAdjacentList.get(z);

            for (int i = 0; i < parents.size(); i++) {
                for (int j = i + 1; j < parents.size(); j++) {
                    int x = parents.get(i);
                    int y = parents.get(j);

                    if (!edgeSet.contains(x + " - " + y) && !edgeSet.contains(y + " - " + x))
                        count++;
                }
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
