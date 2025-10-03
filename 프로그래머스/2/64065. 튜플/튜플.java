import java.util.*;

class Solution {
    public int[] solution(String s) {        
        return solve(s);
    }
    
    public int[] solve(String s){
        
        String[] tuples = extractTuplesFromSet(s);
        
        ArrayList<Integer> ans = new ArrayList();
        Set<Integer> setForCheck = new HashSet();
        ArrayList<Integer>[] tupleList = new ArrayList[tuples.length];
        for(int i = 0 ; i < tuples.length ; i++) {
            tupleList[i] = new ArrayList();
        }
        for(int i = 0 ; i < tuples.length ; i++) {
            insertTupleElementToIntegerArrayList(tuples[i], tupleList[i]);
        }
        
        Arrays.sort(tupleList, ((a,b) -> {
            return a.size() - b.size();
        }));
        
        for(int i = 0 ; i < tuples.length ; i++) {
            ArrayList<Integer> currentArrayList = tupleList[i];
            for(int j = 0 ; j < currentArrayList.size() ; j++) {
                if(setForCheck.add(currentArrayList.get(j))) {
                    ans.add(currentArrayList.get(j));
                }
            }
        }
        
        return ans.stream()
                .filter(i -> i != null)
                .mapToInt(i -> i)
                .toArray();
    }
    
    public String[] extractTuplesFromSet(String s) {
        String[] tuples = s.substring(2, s.length() - 2).split("},\\{");
        for(int i = 0 ; i < tuples.length ; i++) {
            tuples[i] = tuples[i].trim();
        }
        return tuples;
    }
    
    public void insertTupleElementToIntegerArrayList(String tuple, ArrayList<Integer> tupleList) {
        String[] temp = tuple.split(",");

        for(int i = 0 ; i < temp.length ; i++) {
            tupleList.add(Integer.parseInt(temp[i]));
        }
        
    }
}