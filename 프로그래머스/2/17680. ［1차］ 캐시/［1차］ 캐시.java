import java.util.*;

class Solution {
    //  캐시에 사용할 연결리스트를 가지고 있어야 한다.
    //  주요 기능은 다음과 같다.
    //  1. FIFO 구조
    //  2. 호출된 값이 중간에 있다면 삭제하고 마지막 값으로 삽입하기
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Cache cache = new Cache(cacheSize);
        for(String city : cities) {
            city = city.toUpperCase();
            if(cacheSize == 0) {
                answer += 5;
                continue;
            }
            answer += cache.add(city);
        }
        return answer;
    }
    
    class Cache {
        int cacheSize;
        LinkedList<String> linkedList;
        
        Cache(int cacheSize) {
            this.cacheSize = cacheSize;
            linkedList = new LinkedList();
        }
        
        public int add(String s) {
            // 중간에 있으면 찾아서 그걸 삭제하고 Last에 삽입
            int existIndex = linkedList.indexOf(s);
            if(existIndex != -1) {
                linkedList.remove(existIndex);
                linkedList.addLast(s);
                return 1;
            }
            
            // 캐시 사이즈에 도달하지 않았다면 Last에 삽입
            if(linkedList.size() < cacheSize) {
                linkedList.addLast(s);
                return 5;
            }
            
            // 중간에 없다면 첫 번째를 뽑고 Last에 삽입
            linkedList.pollFirst();
            linkedList.addLast(s);
            return 5;
        }
        
        public boolean isExisted(String s) {
            int existIndex = linkedList.indexOf(s);
            if(existIndex != -1) {
                return true;
            }
            return false;
        }
        
        public String poll() {
            if(linkedList.isEmpty()) {
                return null;
            }
            
            return linkedList.pollFirst();
        }
    }
}