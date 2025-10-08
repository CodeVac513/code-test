import java.util.*;

class Solution {
    Set<String> menuSet = new HashSet();
    Map<String, Integer> menuMap = new HashMap();
    
    public String[] solution(String[] orders, int[] course) {
        orders = preprocess(orders);
        String[] answer = solve(orders, course);
        return answer;
    }
    
    public String[] preprocess(String[] orders) {
        String[] newOrders = new String[orders.length];
        for(int i = 0 ; i < orders.length ; i++) {
            String[] tokens = orders[i].split("");
            Arrays.sort(tokens);
            StringBuilder sb = new StringBuilder();
            for(String token : tokens) {
                sb.append(token);
            }
            newOrders[i] = sb.toString();
        }
        return newOrders;
    }
    
    public String[] solve(String[] orders, int[] course) {
        // orders로부터 사용할 메뉴들 추출하기
        for(String order : orders) {
            for(int i = 0 ; i < course.length ; i++) {
                String[] tokens = order.split("");
                combination(tokens, 0, course[i], "");
            }
        }
        
        // 메뉴 조합의 주문 횟수 저장하기
        for(String menu : menuSet) {
            int count = 0;
            for(String order: orders) {
                String[] tokens = menu.split("");
                int temp = 0;
                for(String token : tokens) {
                    if(order.contains(token)) temp++;
                }
                if(temp == tokens.length) count++;
            }
            
            if(count >= 2) {
                menuMap.put(menu, count);
            }
        }
        
        // 가장 주문 횟수가 많은 조합을 answer list에 추가한다.
        ArrayList<String> answer = new ArrayList();
        for(int menuLength : course) {
            
            int max = 0;
            
            for(String key : menuMap.keySet()) {
                if(key.length() != menuLength) continue;
                max = Math.max(max, menuMap.get(key));
            }
            
            for(String key : menuMap.keySet()) {
                if(key.length() == menuLength && menuMap.get(key) == max) {
                    answer.add(key);
                }
            }
        }
        Collections.sort(answer);
        
        return answer.toArray(new String[answer.size()]);
    }
    
    public void combination(String[] tokens, int startIndex, int length, String menu) {
        if (length == 0) {
            menuSet.add(menu);
            return;
        }
        
        for(int i = startIndex; i < tokens.length ; i++) {
            combination(tokens, i + 1, length - 1, menu+tokens[i]);
            
        }
    }
}