import java.io.*;
import java.util.*;

public class Main {
    
    // 조합 계산 함수 C(n, k)
    public static long combination(int n, int k) {
        if (k < 0 || k > n || n < 0) {
            return 0;
        }
        if (k == 0 || k == n) {
            return 1;
        }
        if (k == 1) {
            return n;
        }
        if (k == 2) {
            return (long) n * (n - 1) / 2;
        }
        return 0; // 이 문제에서는 k가 최대 2
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] counter = new int[101];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            
            counter[A]++;
            counter[B]++;
            counter[C]++;
        }
        
        long answer = 0;
        int cannotSelectCount = 0;
        int mustSelectCount = 0;
        
        for (int i = 100; i >= 1; i--) {
            if (counter[i] == 0) {
                answer += combination(
                    99 - cannotSelectCount - mustSelectCount,
                    2 - mustSelectCount
                );
                cannotSelectCount++;
            } else if (counter[i] == 1) {
                mustSelectCount++;
                if (mustSelectCount >= 3) {
                    break;
                }
            }
        }
        
        System.out.println(answer);
    }
}