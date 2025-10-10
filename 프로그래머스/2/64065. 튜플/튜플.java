import java.util.*;

class Solution {
//     <입력>
//         중복되는 원소가 없는 튜플의 조합(combination)을 ,로 구분하는 String
//     <출력>
//         역순으로 따라가려면, 이 조합을 정렬해야 함. 조합의 길이가 짧을수록 먼저 나오는 원소임.
        
//     1. 입력을 분해해서 ArrayList의 배열로 분배한다.
//     2. 분배된 배열을 정렬한다. (ArrayList의 길이 기준으로)
//     3. 길이가 짧은 ArrayList의 원소와 이미 추출된 원소가 저장되는 set과 비교해서 새로운 원소인지 확인한다.
//         - 새로운 원소라면 배열에 추가
//         - 있던 원소라면 continue;
    public int[] solution(String s) {
        ArrayList<Integer>[] arrayLists = stringToCombination(s);
        int[] answer = arrayListToTuple(arrayLists);
        return answer;
    }
    
    public ArrayList<Integer>[] stringToCombination(String s) {
        String temp = s.substring(1, s.length() - 1);
        
        // 중괄호를 이스케이프해서 사용해야 함.
        String[] tokens = temp.split("\\},\\{");
        for(int i = 0 ; i < tokens.length ; i++) {
            tokens[i] = tokens[i].replace("{", "").replace("}", "");
        }
        
        int N = tokens.length;
        ArrayList<Integer>[] result = new ArrayList[N];
        for(int i = 0 ; i < N ;i++) {
            int[] a = Arrays.stream(tokens[i].split(",")).mapToInt(Integer::parseInt).toArray();
            result[i] = new ArrayList();
            for(int element : a) {
                result[i].add(element);
            }
        }
        
        Arrays.sort(result, (a,b) -> a.size() - b.size());
        
        return result;
    }
    
    public int[] arrayListToTuple(ArrayList<Integer>[] arrayLists) {
        Set<Integer> tupleElementSet = new HashSet();
        ArrayList<Integer> answer = new ArrayList();
        for(ArrayList<Integer> list : arrayLists) {
            for(Integer element : list) {
                if(tupleElementSet.add(element)) {
                    answer.add(element);
                }
            }
        }
        
        int[] result = new int[answer.size()];
        for(int i = 0 ; i < answer.size() ; i++) {
            result[i] = answer.get(i);
        }
        return result;
    }
}
