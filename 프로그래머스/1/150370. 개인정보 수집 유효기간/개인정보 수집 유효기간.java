import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> list = new ArrayList<>();
        Map<String, Integer> hm = new HashMap<>();
        
        for(int i = 0; i < terms.length; i++){
            String[] ss = terms[i].split(" ");
            hm.put(ss[0], Integer.parseInt(ss[1]));
        }
        
        for(int i = 0; i < privacies.length; i++){
            String[] ss = privacies[i].split(" ");
            int num = hm.get(ss[1]);
            
            StringTokenizer st = new StringTokenizer(ss[0], ".");
            int year = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken()) + num;
            int day = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(today, ".");
            int iYear = Integer.parseInt(st.nextToken());
            int iMonth = Integer.parseInt(st.nextToken());
            int iDay = Integer.parseInt(st.nextToken());
            
            long l1 = year * 12L * 28L + month * 28L + day;
            long l2 = iYear * 12L * 28L + iMonth * 28L + iDay;
            
            if(l1 <= l2) list.add(i + 1);
        }
        
        return list.stream().mapToInt(i -> i).toArray();
    }
}