import java.util.*;

class Solution {
    // 여러 손님이 각각 시킨 단품 메뉴 리스트가 있음.
    // 이 리스트를 참고해서 세트 메뉴를 만들거임.
    // 메뉴는 최소 2명 이상이 시킨 것들로 조합을 할 것이며, 메뉴 구성은 가장 많이 시킨 조합을 선택.
    // <입력>
    //     orders 배열 : 2이상, 20이하의 크기
    //     orders 원소의 길이 : 2이상, 10이하의 문자열 (대문자 알파벳으로만 구성, 중복된 문자 X)
    //     course 배열 : 1이상 10이하의 크기
    //     course 원소 : 2이상 10이하의 자연수 (세트 메뉴에 들어갈 단품 메뉴의 개수)
    // <출력>
    //     세트 메뉴 구성 문자열을 모아놓은 배열
    // 1. 문자열을 토큰으로 쪼개서 단품 메뉴 배열로 가지고 있기
    //     - 이 때, 문자열을 정렬해야 조합을 사용할 수 있음.
    // 2. 단품 메뉴를 코스 원소에 따라 길이를 정하고, 조합을 활용해서 모든 세트 메뉴 구성을 만들기.
    //     - Map 과 getOrDefault를 활용해서 count하기.
    // 3. count값을 통해서 제일 많이 주문한 세트들을 골라내고 반환하기
    
    int max;
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        String[][] singleMenusArray = new String[orders.length][0];
        int iter = 0;
        for(String order : orders) {
            singleMenusArray[iter] = stringToArray(order);
            iter++;
        }
        answer = getAnswer(singleMenusArray, course);
        return answer;
    }
    
    public String[] getAnswer(String[][] singleMenusArray, int[] course) {
        ArrayList<String> result = new ArrayList();
        
        for(int courseCount : course) {
            max = 0;
            HashMap<String, Integer> menuMap = createMenuMap(singleMenusArray, courseCount);
            for(String setMenu : menuMap.keySet()) {
                int currentCount = menuMap.get(setMenu);
                if(max == currentCount) result.add(setMenu);
            }
        }
        String[] ans = result.toArray(new String[0]);
        Arrays.sort(ans);
        return ans;
    }
    
    public HashMap<String, Integer> createMenuMap(String[][] singleMenusArray, int courseCount) {
        String[] menuCombination = getCombination(singleMenusArray, courseCount);
        HashMap<String, Integer> menuMap = new HashMap();
        for(String menu : menuCombination) {
            int currentCount = menuMap.getOrDefault(menu, 0) + 1;
            menuMap.put(menu, currentCount);
            max = Math.max(max, currentCount);
        }
        
        return menuMap;
    }
    
    public String[] getCombination(String[][] singleMenusArray, int courseCount) {
        ArrayList<String> totalResult = new ArrayList();
        HashMap<String, Integer> countMap = new HashMap();
        for(String[] singleMenus : singleMenusArray) {
            ArrayList<String> result = new ArrayList();
            combination(singleMenus, "", result, courseCount, 0);
            for (String menus : result) {
                countMap.put(menus, countMap.getOrDefault(menus, 0) + 1);
                int currentCount = countMap.getOrDefault(menus, 0);
                if(currentCount >= 2) totalResult.add(menus);
            }
        }
        
        return totalResult.toArray(new String[0]);
        
    }
    
    public void combination(String[] singleMenus, String ans, ArrayList<String> result, int courseCount, int startIndex) {
        if(courseCount == 0) {
            result.add(ans);
            return;
        }        
        for(int i = startIndex ; i < singleMenus.length; i++) {
            combination(singleMenus, ans+singleMenus[i], result, courseCount - 1, i + 1);
        }
    }
    
    
    public String[] stringToArray(String order) {
        String[] tokens = order.split("");
        Arrays.sort(tokens);
        return tokens;
    }
}