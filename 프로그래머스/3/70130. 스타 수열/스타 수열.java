import java.util.*;

class Solution {
    public int solution(int[] a) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i <= 500_000; i++) list.add(new ArrayList<>());
        
        for(int i = 0; i < a.length; i++){
            int k = a[i];
            list.get(k).add(i);
        }
        
        int maxLen = 0;
        for(int i = 0; i <= 500_000; i++){
            List<Integer> idxList = list.get(i);
            if(idxList.size() == 0) continue;
            
            int prob = 0;
            int prev = -1;
            for(int idx : idxList){
                if(idx - 1 >= 0 && a[idx - 1] != i && idx - 1 != prev){
                    prob++;
                    prev = idx - 1;
                }else if(idx + 1 < a.length && a[idx + 1] != i && idx + 1 != prev){
                    prob++;
                    prev = idx + 1;
                }
            }
            maxLen = Math.max(maxLen, prob);
        }
        return maxLen * 2;
    }
}