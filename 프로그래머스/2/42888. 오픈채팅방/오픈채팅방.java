import java.lang.*;
import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[] answer = getAnswerFromRecord(record);
        return answer;
    }
    
    public String[] getAnswerFromRecord(String[] record) {
        
        HashMap<String, String> nicknameMap = new HashMap();
        
        setNicknameMap(nicknameMap, record);
        
        return makeAnswerArrayWithMap(nicknameMap, record);
    }
    
    public void setNicknameMap(HashMap<String, String> nicknameMap, String[] record) {
        for(String s : record) {
            String[] tokens = s.split(" ");
            if(tokens[0].equals("Enter")) {
                nicknameMap.put(tokens[1], tokens[2]);
            } else if (tokens[0].equals("Change")) {
                nicknameMap.put(tokens[1], tokens[2]);
            }
        }
    }
    
    public String[] makeAnswerArrayWithMap(HashMap<String, String> nicknameMap, String[] record) {
        ArrayList<String> answer = new ArrayList();
        for(String s : record) {
            String[] tokens = s.split(" ");
            StringBuilder sb = new StringBuilder();
            String currentNickName = nicknameMap.get(tokens[1]);
            if(tokens[0].equals("Enter")) {
                sb.append(currentNickName);
                sb.append("님이 들어왔습니다.");
                answer.add(sb.toString());
            } else if (tokens[0].equals("Leave")) {
                sb.append(currentNickName);
                sb.append("님이 나갔습니다.");
                answer.add(sb.toString());
            }
        }
        
        return answer.toArray(new String[answer.size()]);
    }
}