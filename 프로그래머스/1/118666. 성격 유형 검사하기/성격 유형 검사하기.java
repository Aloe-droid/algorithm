import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<String, Integer> hm = new HashMap<>();
        hm.put("R", 0);
        hm.put("T", 0);
        hm.put("C", 0);
        hm.put("F", 0);
        hm.put("J", 0);
        hm.put("M", 0);
        hm.put("A", 0);
        hm.put("N", 0);
        
        for(int i = 0; i < survey.length; i++){
            String s = survey[i];
            int val = choices[i];
            
            if(val == 4) continue;
            
            if(val <= 3){
                String key = s.substring(0, 1);
                int point = 4 - val;
                hm.put(key, hm.get(key) + point);
            }else{
                String key = s.substring(1, 2);
                int point = val - 4;
                hm.put(key, hm.get(key) + point);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        int i1 = hm.get("R");
        int i2 = hm.get("T");
        if(i1 >= i2) sb.append("R");
        else sb.append("T");
        
        i1 = hm.get("C");
        i2 = hm.get("F");
        if(i1 >= i2) sb.append("C");
        else sb.append("F");
        
        i1 = hm.get("J");
        i2 = hm.get("M");
        if(i1 >= i2) sb.append("J");
        else sb.append("M");
        
        i1 = hm.get("A");
        i2 = hm.get("N");
        if(i1 >= i2) sb.append("A");
        else sb.append("N");
        
        return sb.toString();
    }
}