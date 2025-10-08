class Solution {
    // 최대한 cap 제한을 끝까지 사용하는게 좋음.
    // 1. 최대한 마지막 집의 택배를 먼저 가져다 주는 게 좋음.
    // 2. 돌아올 때, 최대한 마지막 집의 빈 박스를 많이 들고 와야 함.
    // 그 모든 건 거리로 계산함.
    // 각 집은 사이의 거리가 1임.
    int cap; 
    int n; 
    int[] deliveries; 
    int[] pickups;
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        this.cap = cap;
        this.n = n;
        this.deliveries = deliveries;
        this.pickups = pickups;

        long answer = greedy();
        
        return answer;
    }
    
    public long greedy() {
        long moved = 0L;
        int deliveryIdx = n - 1;  // 배달해야 할 마지막 집
        int pickupIdx = n - 1;    // 수거해야 할 마지막 집
        while(deliveryIdx >= 0 || pickupIdx >= 0) {
            while(deliveryIdx >= 0 && deliveries[deliveryIdx] == 0) {
                deliveryIdx--;
            }
            while(pickupIdx >= 0 && pickups[pickupIdx] == 0) {
                pickupIdx--;
            }
            // 움직여야 하는 max 거리 측정
            int max = Math.max(deliveryIdx, pickupIdx);
            
            // 모든 인덱스가 음수이면 모든 작업이 완료된 것
            if(deliveryIdx < 0 && pickupIdx < 0) break;
            
            // 택배 적재
            int currentCap = 0;
            for(int i = deliveryIdx ; i >= 0;i--) {
                if(currentCap == cap) break;
                if(deliveries[i] == 0) continue;
                
                if(deliveries[i] > cap - currentCap) {
                    deliveries[i] -= (cap - currentCap);
                    currentCap += (cap - currentCap);
                } else {
                    currentCap += deliveries[i];
                    deliveries[i] = 0;
                    deliveryIdx = i - 1;
                }
            }
           
            // 빈 박스 수거
            currentCap = 0;
            for(int i = pickupIdx ; i >= 0;i--) {
                if(currentCap == cap) break;
                if(pickups[i] == 0) continue;
                
                if(pickups[i] > cap - currentCap) {
                    pickups[i] -= (cap - currentCap);
                    currentCap += (cap - currentCap);
                } else {
                    currentCap += pickups[i];
                    pickups[i] = 0;
                    pickupIdx = i - 1;
                }
            }
            
            moved += (max + 1) * 2;
        }
        return moved;
    }
}