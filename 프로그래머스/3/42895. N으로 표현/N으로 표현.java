import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if(N == number) return 1;
        
        List<Set<Integer>> list = new ArrayList<>();
        for(int i = 0; i <= 8; i++) list.add(new HashSet<>());
        
        list.get(1).add(N);
        
        for(int i = 1; i <= 8; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < i; j++) sb.append(N);
            list.get(i).add(Integer.parseInt(sb.toString()));
            
            for(int j = 1; j < i; j++){
                Set<Integer> s1 = list.get(j);
                Set<Integer> s2 = list.get(i - j);
                
                for(int k1 : s1){
                    for(int k2 : s2){
                        list.get(i).add(k1 + k2);
                        list.get(i).add(k1 - k2);
                        list.get(i).add(k2 - k1);
                        list.get(i).add(k1 * k2);
                        if(k2 != 0) list.get(i).add(k1 / k2);
                        if(k1 != 0) list.get(i).add(k2 / k1);
                    }
                }
            }
        }
        
        
        for(int i = 1; i <= 8; i++){
            if(list.get(i).contains(number)) return i;
        }
        return -1;
    }
}