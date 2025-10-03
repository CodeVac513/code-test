import java.util.*;
import java.lang.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        FastQueue cache = new FastQueue();
        return solve(cache, cities, cacheSize);
    }
    
    public int solve(FastQueue cache, String[] cities, int cacheSize) {
        int totalTime = 0;
        for(String currentCity : cities) {
            currentCity = currentCity.toUpperCase();
            if(cacheSize == 0) {
                totalTime += 5;
                continue;
            }
            
            if(cache.size() < cacheSize) {
                if(cache.contains(currentCity)) {
                    totalTime += 1;
                    cache.moveToLast(currentCity);
                } else {
                    totalTime += 5;
                    cache.offer(currentCity);
                }
                continue;
            }
            
            if(cache.contains(currentCity)) {
                totalTime += 1;
                cache.moveToLast(currentCity);
            } else {
                totalTime += 5;
                cache.poll();
                cache.offer(currentCity);
            }
        }
        return totalTime;
    }
    
    class FastQueue {
        LinkedList<String> list;
        Map<String, Integer> dataMap;
        
        FastQueue () {
            list = new LinkedList();
            dataMap = new HashMap();
        }
        
        public void offer(String data) {
            list.addLast(data);
            updateMap();
        }
        
        public String poll() {
            if(list.isEmpty()) return null;
            String item = list.removeFirst();
            updateMap();
            return item;
        }
        
        public boolean moveToLast(String item) {
            if(!dataMap.containsKey(item)) return false;
            
            list.remove(item);
            list.addLast(item);
            updateMap();
            return true;
        }
        
        public boolean contains(String item) {
            return dataMap.containsKey(item);
        }
        
        public boolean isEmpty() {
            return list.isEmpty();
        }
        
        public int size() {
            return list.size();
        }
        
        private void updateMap() {
            dataMap.clear();
            for(int i = 0 ; i < list.size() ; i++) {
                dataMap.put(list.get(i), i);
            }
        }
       
    }
}