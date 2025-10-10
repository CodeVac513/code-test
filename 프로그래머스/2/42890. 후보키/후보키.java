import java.util.*;

class Solution {
    // 유일성과 최소성 <- 어떤 데이터들의 유일한 조합인지 찾아야 함.
    // 일단 조합을 찾는 건 맞음. 모든 조합을 검사해봐야 하니까.
    // HashMap으로 key를 0, 1, 2, 3, 01, 02, 03, 12, 13, 23, ... 0123 이렇게 만들어두고
    // 그 안에 value로 Set을 만들어서
    // Set 안에 false가 반환되는 순간 버리고 다음 key로 넘어가는 식으로
    // 그러면 HashMap을 두 개 만들어야 함. 하나는 이게 되는지 안되는지 파악하는 boolean과 실제 데이터 검사를 위한 Set을 저장하는.
    
    HashMap<String, Set<String>> combinationMap;
    HashMap<String, Boolean> checkMap;
    String[][] relation;
    
    public int solution(String[][] relation) {
        this.relation = relation;
        initMap();
        for(String[] tuple : relation) {
            getCombination(tuple);
        }
        ArrayList<String> temp = new ArrayList();
        for(String key : checkMap.keySet()) {
            if(checkMap.get(key)) {
                temp.add(key);
            }
        }
        
        ArrayList<String> answer = new ArrayList();
        Collections.sort(temp, (a, b) -> {
            return a.length() - b.length();
        });
        
        for(String s : temp) {
            boolean flag = true;
            for(String a : answer) {
                // a의 모든 문자가 s에 포함되어 있으면 최소성 위반
                if(Arrays.stream(a.split("")).allMatch(token -> s.contains(token))) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                answer.add(s);
            }
        }
        
        return answer.size();
    }
    
    public void initMap() {
        combinationMap = new HashMap();
        checkMap = new HashMap();
        int keySize = relation[0].length;
        int[] keyIndex = new int[keySize];
        for(int i = 0 ; i < keySize ; i++) {
            keyIndex[i] = i;
        }
        ArrayList<String> result = new ArrayList();
        for(int i = 1 ; i <= keySize ; i++) {
            combination(keyIndex, 0, i, "", result);
        }
        
        for(String s : result) {
            combinationMap.put(s, new HashSet());
            checkMap.put(s, true);
        }
    }

    public void combination(int[] keyIndex, int startIndex, int keyLength, String currentString, ArrayList<String> result) {
        if (keyLength == 0) {
            result.add(currentString);
            return;
        }
        
        for (int i = startIndex ; i < keyIndex.length ; i++) {
            combination(keyIndex, i + 1, keyLength - 1, currentString+keyIndex[i], result);
        }
    }
    
    public void getCombination(String[] tuple) {
         HashMap<String, ArrayList<String>> result = new HashMap();
        for(int i = 1 ; i <= tuple.length ; i++) {
            combination(tuple, 0, i, "", "", result);
        }
        
        for(String key : result.keySet()) {
            if(checkMap.get(key)) {
                for(String s : result.get(key)) {
                    Set<String> currentSet = combinationMap.get(key);
                    if(!currentSet.add(s)) {
                        checkMap.put(key, false);
                        break;
                    }
                }
            }
        }
    }
    
    public void combination(String[] tuple, int startIndex, int columnCount, String currentIndex, String currentString, HashMap<String, ArrayList<String>> result) {
        if (columnCount == 0) {
            ArrayList<String> temp = result.getOrDefault(currentIndex, new ArrayList());
            temp.add(currentString);
            result.put(currentIndex, temp);
            return;
        }
        
        for (int i = startIndex ; i < tuple.length ; i++) {
            combination(tuple, i + 1, columnCount - 1, currentIndex+i, currentString+tuple[i],result);
        }
    }
    
}
