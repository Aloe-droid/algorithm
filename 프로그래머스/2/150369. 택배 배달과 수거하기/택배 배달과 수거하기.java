import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long cnt = 0;
        
        int delIdx = -1;
        int pckIdx = -1;
        
        boolean b1 = false;
        boolean b2 = false;
            
        for(int i = n - 1; i >= 0; i--){
            if(deliveries[i] != 0 && !b1){
                delIdx = i;
                b1 = true;
            }
                
            if(pickups[i] != 0 && !b2){
                pckIdx = i;
                b2 = true;
            }
        }
        
        while(delIdx >= 0 || pckIdx >= 0){
            cnt += Math.max(delIdx, pckIdx) + 1L;
            
            int diff = cap;
            int k = delIdx;
            while(k >= 0){
                if(deliveries[k] > diff){
                    deliveries[k] -= diff;
                    break;
                }else{
                    diff -= deliveries[k];
                    deliveries[k] = 0;
                    k--;
                }
            }
            delIdx = k;
            
            diff = cap;
            k = pckIdx;
            while(k >= 0){
                if(pickups[k] > diff){
                    pickups[k] -= diff;
                    break;
                }else{
                    diff -= pickups[k];
                    pickups[k] = 0;
                    k--;
                }
            }
            pckIdx = k;
        }
        
        return cnt * 2;
    }
}