import java.util.*;

class Solution {
    public int[] solution(String[] idList, String[] report, int k) {
        Map<String, Set<String>> hm = new HashMap<>();
        Map<String, Integer> cnt = new HashMap<>();
        Set<String> set = new HashSet<>();
        int[] ans = new int[idList.length];
        
        for(String id : idList){
            hm.put(id, new HashSet<>());
            cnt.put(id, 0);
        }
        
        for(String rep : report){
            StringTokenizer st = new StringTokenizer(rep, " ");
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            hm.get(s1).add(s2);
        }
        
        for(String s : hm.keySet()){
            Set<String> hs = hm.get(s);
            for(String name : hs) cnt.put(name, cnt.get(name) + 1);
        }
        
        for(String s : cnt.keySet()) if(cnt.get(s) >= k) set.add(s);
        for(int i = 0; i < idList.length; i++){
            String s = idList[i];
            hm.get(s).retainAll(set);
            ans[i] = hm.get(s).size();
        }
        
        return ans;
    }
}