import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int iter = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            if (N == 0)
                break;

            bw.write("Simulation " + iter + "\n");

            LRUCache cache = new LRUCache(N);
            String sequence = st.nextToken();

            for (char c : sequence.toCharArray()) {
                if (c == '!') {
                    bw.write(cache.getCache() + "\n");
                } else {
                    cache.access(c);
                }
            }

            iter++;
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static class LRUCache {
        private LinkedList<Character> cache;
        private Set<Character> cacheSet;
        private int capacity;

        LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new LinkedList<>();
            this.cacheSet = new HashSet<>();
        }

        public void access(char data) {
            if (cacheSet.contains(data)) {
                cache.remove(Character.valueOf(data));
                cache.addLast(data);
            } else {
                if (cache.size() >= capacity) {
                    char removed = cache.pollFirst();
                    cacheSet.remove(removed);
                }
                cache.addLast(data);
                cacheSet.add(data);
            }
        }

        public String getCache() {
            StringBuilder sb = new StringBuilder();
            for (char c : cache) {
                sb.append(c);
            }
            return sb.toString();
        }
    }
}