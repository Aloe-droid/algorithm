import java.io.*;
import java.util.*;

class Solution {
    Map<String, Map<String, Integer>> hm;
    Map<String, Integer> cntMap;
    
    public int solution(String[] friends, String[] gifts) {
        hm = new HashMap<>();
        cntMap = new HashMap<>();
        for(String s : friends){
            hm.put(s, new HashMap<>());
            cntMap.put(s, 0);
        }
        
        for(String s : gifts){
            StringTokenizer st = new StringTokenizer(s);
            String p1 = st.nextToken();
            String p2 = st.nextToken();
            Map<String, Integer> temp = hm.get(p1);
            temp.put(p2, temp.getOrDefault(p2, 0) + 1);
            cntMap.put(p1, cntMap.get(p1) + 1);
            cntMap.put(p2, cntMap.get(p2) - 1);
        }
        
        int[] next = new int[friends.length];
        for(int i = 0; i < friends.length -1; i++){
            for(int j = i + 1; j < friends.length; j++){
                int k1 = hm.get(friends[i]).getOrDefault(friends[j], 0);
                int k2 = hm.get(friends[j]).getOrDefault(friends[i], 0);
                int p1 = cntMap.get(friends[i]);
                int p2 = cntMap.get(friends[j]);
                
                if(k1 > k2) next[i]++;
                else if(k1 < k2) next[j]++;
                else if(p1 < p2) next[j]++;
                else if(p1 > p2) next[i]++;
            }
        }
        
        int max = 0;
        for(int i : next) max = Math.max(max, i);
        return max;
    }
}