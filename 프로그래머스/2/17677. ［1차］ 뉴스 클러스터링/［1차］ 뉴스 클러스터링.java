import java.util.*;

class Solution {
    Map<String, Integer> hm1, hm2;
    
    public int solution(String str1, String str2) {
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        hm1 = new HashMap<>();
        hm2 = new HashMap<>();
        
        for(int i = 0; i < str1.length() - 1; i++){
            String s = str1.substring(i, i + 2);
            if(s.charAt(0) < 'A' || s.charAt(0) > 'Z') continue;
            if(s.charAt(1) < 'A' || s.charAt(1) > 'Z') continue;
            hm1.put(s, hm1.getOrDefault(s, 0) + 1);
        }
        
        for(int i = 0; i < str2.length() - 1; i++){
            String s = str2.substring(i, i + 2);
            if(s.charAt(0) < 'A' || s.charAt(0) > 'Z') continue;
            if(s.charAt(1) < 'A' || s.charAt(1) > 'Z') continue;
            hm2.put(s, hm2.getOrDefault(s, 0) + 1);
        }
        
        HashMap<String, Integer> sum = new HashMap<>();
        HashMap<String, Integer> sub = new HashMap<>();
        
        for(String s : hm1.keySet()){
            int c1 = hm1.get(s);
            int c2 = hm2.getOrDefault(s, 0);
            
            int max = Math.max(c1, c2);
            int min = Math.min(c1, c2);
            sum.put(s, max);
            sub.put(s, min);
            
            hm2.remove(s);
        }
        
        for(String s : hm2.keySet()) sum.put(s, hm2.get(s));
        
        int c1 = 0;
        int c2 = 0;
        float f = 0f;
        for(String s : sub.keySet()) c1 += sub.get(s);
        for(String s : sum.keySet()) c2 += sum.get(s);
        
        if(c2 == 0) f = 1f;
        else f = c1 / (float) c2;
        f *= 65536;
        
        return (int) f;
    }
}