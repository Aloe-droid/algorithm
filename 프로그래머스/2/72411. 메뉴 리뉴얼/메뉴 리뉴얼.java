import java.util.*;

class Solution {
    static List<Map<String, Integer>> list;
    static int[] counts;
    static String str;
    
    public String[] solution(String[] orders, int[] course) {
        list = new ArrayList<>();
        counts = new int[11];
        for(int i = 0; i <= 10; i++) list.add(new HashMap<>());
        
        for(String s : orders){
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            str = new String(chars);
            for(int i = 2; i <= str.length(); i++){
                combination(0, 0, i, new char[i]);
            }
        }
        
        List<String> ans = new ArrayList<>();
        for(int c : course){
            if(counts[c] < 2) continue;
            
            for(String s : list.get(c).keySet()){
                if(list.get(c).get(s) == counts[c]) ans.add(s);
            }
        }
        
        Collections.sort(ans);
        return ans.toArray(new String[0]);
    }
    
    public static void combination(int start, int idx, int k, char[] chars){
        if(idx == k){
            String s = new String(chars);
            int index = s.length();
            Map<String, Integer> hm = list.get(index);
            hm.put(s, hm.getOrDefault(s, 0) + 1);
            counts[index] = Math.max(counts[index], hm.get(s));
            return;
        }
        
        for(int i = start; i < str.length(); i++){
            chars[idx] = str.charAt(i);
            combination(i + 1, idx + 1, k, chars);
        }
    }
}