import java.util.*;

class Solution {
    public String[] solution(String[] ss) {
        String[] ans = new String[ss.length];
        
        for(int i = 0; i < ss.length; i++){
            StringBuilder s = new StringBuilder(ss[i]);
            int cnt = 0;
            int idx = 0;
            
            // 110 있는 부분 전부 자르기 
            while(idx < s.length() - 2){
                if(s.charAt(idx) == '1' && s.charAt(idx + 1) == '1' && s.charAt(idx + 2) == '0'){
                    cnt++;
                    s.delete(idx, idx + 3);
                    idx -= 2;
                    if(idx < 0) idx = 0;
                }else{
                    idx++;
                }
            }
            
            // 110 합칠 공간 찾기 
            idx = 0;
            while(idx < s.length()){
                int len = 3;
                if(idx + len >= s.length()) len = s.length() - idx;
                
                StringBuilder sub = new StringBuilder(s.substring(idx, idx + len));
                while(sub.length() < 3) sub.append("1");
                
                if(Integer.parseInt(sub.toString(), 2) > 6) break;                
                idx++;
            }
            
            StringBuilder sb = new StringBuilder(s);
            for(int j = 0; j < cnt; j++) sb.insert(idx, "110");
            ans[i] = sb.toString();
        }
        return ans;
    }
}