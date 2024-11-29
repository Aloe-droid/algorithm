import java.util.*;

class Solution {
    public static int[] dx = {1,-1, 0, 0};
    public static int[] dy = {0, 0,-1, 1};
    
    public int solution(String dirs) throws Exception {
        Map<Integer, Set<Integer>> hm = new HashMap<>();
        int N = 11;
        int x = 5, y = 5;
        
        for(int i = 0; i < dirs.length(); i++){
            char c = dirs.charAt(i);
            int idx = -1;
            
            if(c == 'U') idx = 0;
            else if(c == 'D') idx = 1;
            else if(c == 'L') idx = 2;
            else if(c == 'R') idx = 3;
            
            if(idx == -1) throw new RuntimeException("방향 에러");
            
            int nx = x + dx[idx];
            int ny = y + dy[idx];
            
            if(nx < 0 || nx >= N || ny < 0 || ny >= N ) continue;
            
            int key1 = x * N + y;
            int key2 = nx * N + ny;
            
            Set<Integer> hs1 = hm.getOrDefault(key1, new HashSet<>());
            Set<Integer> hs2 = hm.getOrDefault(key2, new HashSet<>());
            hs1.add(key2);
            hs2.add(key1);
            hm.put(key1, hs1);
            hm.put(key2, hs2);
            
            x = nx;
            y = ny;
        }
        
       for(int key : hm.keySet()){
           for(int value : hm.get(key)){
               if(hm.containsKey(value)){
                   hm.get(value).remove(key);
               }
           }
       }
        
        int count = 0;
        for(int key : hm.keySet()) count += hm.get(key).size();
        return count;
    }
}