import java.util.*;

class Solution {
    public int solution(String s) {
        if(s.length() == 1) return 1;
        int minLen = Integer.MAX_VALUE;
        
        // 자르는 단위
        for(int i = 1; i <= s.length() / 2; i++) {
            int cnt = 0;
            for(int x = 0; x < s.length(); x++) {
                if(x + i > s.length()) {
                    cnt += s.length() - x;
                    break;
                }
                
                String s1 = s.substring(x, x + i);
                boolean flag = false;
                int temp = 0;
                
                for(int y = 2; y < s.length(); y++) {
                    if(x + i * y > s.length()) {
                        x += ((y - 1) * i) - 1;
                        break;
                    }
                    
                    String s2 = s.substring(x + i * (y - 1) , x + i * y);
                    if(s1.equals(s2)) {
                        flag = true;
                        temp++;
                    } else {
                        x += ((y - 1) * i) - 1;
                        break;
                    }
                }
                
                if(flag) {
                    cnt += Integer.toString(temp + 1).length() + s1.length(); 
                }else {
                    cnt += s1.length(); 
                }
            }
            
            minLen = Math.min(minLen, cnt);
        }
        
        return minLen;
    }
}