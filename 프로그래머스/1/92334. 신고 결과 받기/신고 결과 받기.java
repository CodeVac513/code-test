import java.util.*;

class Solution {
    // 특징
    // - 동일 인물이 한 명의 유저를 여러 번 신고해도 1번으로 처리된다.
    // - k번 이상 신고된 유저는 게시판 이용이 정지된다. -> 해당 유저를 신고한 모든 유저가 정지 사실을 메일로 받는다.
    // <입력>
    //     - 아이디 문자열 리스트
    //     - "신고한사람 신고당한사람" 문자열 형태의 리스트
    //     - k는 정지 기준
    // <출력> 
    //     - 신고한 유저들이 받은 결과 메일의 수
    // 1. Map에 key로는 신고 당한 유저, value로는 신고한 유저 Set으로 선언한다.- 여러 번 신고해도 동일 인물의 신고는 1번으로
    // 2. report를 돌면서 해당 Map을 채운다.
    // 3. Map의 value 길이가 k가 넘어가면 Set에 있는 사람들의 목록을 보면서 emailMap에 몇 개의 이메일을 받을지 정리한다.
    // 4. emailMap과 id_list를 대조해서 int 배열을 채운다.
    Map<String, Set<String>> reportMap;
    Map<String, Integer> emailMap;
    ArrayList<String> bannedList;
    
    public int[] solution(String[] id_list, String[] report, int k) {
        bannedList = new ArrayList();
        reportMap = new HashMap();
        emailMap = new HashMap();
        for(String r : report) {
            putData(r);
        }
        banUser(id_list, k);
        return getAnswer(id_list);
    }
    
    public void putData(String r) {
        String[] names = r.split(" ");
        
        Set<String> nameSet = reportMap.getOrDefault(names[1], new HashSet());
        nameSet.add(names[0]);
        
        reportMap.put(names[1], nameSet);
    }
    
    public void banUser(String[] idList, int k) {
        for(String reportedUser : reportMap.keySet()) {
            Set<String> nameSet = reportMap.get(reportedUser);
            if(nameSet.size() >= k) {
                bannedList.add(reportedUser);
                for(String name : nameSet) {
                    emailMap.put(name, emailMap.getOrDefault(name, 0) + 1);
                }
            }
        }
    }
    
    public int[] getAnswer(String[] idList) {
        int[] answer = new int[idList.length];
        for(int i = 0 ; i < idList.length ; i++) {
            answer[i] = emailMap.getOrDefault(idList[i], 0);
        }
        
        return answer;
    }
}
