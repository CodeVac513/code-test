import java.util.*;

class Solution {
//     1. 일단 info에 데이터들을 나눠야 할 것 같은데...
//        - 나눈 데이터는 Hash Table에 저장해야 할 것 같음?
//        이게 데이터베이스 쿼리문을 본딴 문제인 것 같은데...
//        Hash에 저장한다고 해서 다음 조건들을 어떻게 관리해야 할까?
//     2. query의 와일드카드인 -를 어떻게 해야 할까?
    
//     --- 힌트 보고 난 후
        
//     1. 모든 info에 있는 데이터를 나눠서 HashMap의 Key로 만들어야 한다.
//     2. Value로는 ArrayList를 설정해서 정렬한 채로 저장해야 한다.
//     3. 사람 한 명이 여러 조합에 들어갈 수 있으니고, 와일드카드를 고려해서 java backend junior pizza의 조건을 가정하면 2^4개의 key가 만들어질 것. 
//     4. 이런 상황에서 list의 값을 빨리 찾으려면 바이너리 서치가 필요할 듯.
    public int[] solution(String[] info, String[] query) {
        ArrayList<Integer> answer = new ArrayList();
        Map<String, ArrayList<Integer>> databaseMap = infoToHashMap(info);
        for(String a : query) {
            String[] tokens = queryToKey(a).split(" ");
            String key = tokens[0]+" "+tokens[1]+" "+tokens[2]+" "+tokens[3];
            int score = Integer.parseInt(tokens[4]);
            if(databaseMap.get(key) == null) {
                answer.add(0);
                continue;
            }
            answer.add(binarySearch(databaseMap.get(key), score));
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    
    }
    
    public int binarySearch(ArrayList<Integer> array, int score) {
        int left = 0;
        int right = array.size() - 1;
        
        int index = array.size();
        while(left <= right) {
            int mid = left + (right - left) / 2;
            int currentScore = array.get(mid);

            if(score <= currentScore) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return array.size() - index;
    }
    
    public String queryToKey(String query) {
        String[] tokens = Arrays.stream(query.split(" ")).filter(a -> !a.equals("and")).toArray(String[]::new);
        StringBuilder sb = new StringBuilder();
        for(String token : tokens) {
            sb.append(token+" ");
        }
        return sb.toString().trim();
    }
    
    public Map<String, ArrayList<Integer>> infoToHashMap(String[] infoArray) {
        HashMap<String, ArrayList<Integer>> hashMap = new HashMap();
        for(String info : infoArray) {
            String[] tokens = info.split(" ");
            int score = Integer.parseInt(tokens[4]);
            dfs(tokens, 0, "", score, hashMap);
        }
        
        for(ArrayList<Integer> list : hashMap.values()) {
            Collections.sort(list);
        }
        
        return hashMap;
    }
    
    public void dfs(String[] tokens, int idx, String current, int score, 
                Map<String, ArrayList<Integer>> hashMap) {
        if(idx == 4) {
            String key = current.trim();
            hashMap.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
            return;
        }

        dfs(tokens, idx + 1, current + tokens[idx] + " ", score, hashMap);
        dfs(tokens, idx + 1, current + "- ", score, hashMap);
    }

}
