import java.util.*;

class Solution {
    // 유니온 파인드 or BFS
    int[] parent;
    int n;
    public int solution(int n, int[][] computers) {
        this.n = n;
        parent = new int[n+1];
        for(int i = 0 ; i < n+1 ; i++) {
            parent[i] = i;
        }
        
        for(int i = 0 ; i < n ; i++) {
            for(int j  = 0 ; j < n ; j++) {
                int status = computers[i][j];
                if(status == 1) {
                    union(i + 1, j + 1);       
                }
            }
        }
        Set<Integer> answerSet = new HashSet();
        for(int i = 1 ; i <= n ; i++) {
            answerSet.add(find(i));
        }
        return answerSet.size();
    }
    
    public void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if (a != b) {
            parent[b] = a;
        }
    }
    
    public int find(int a) {
        if(parent[a] == a) {
            return a;
        }
        
        return parent[a] = find(parent[a]);
    }
}