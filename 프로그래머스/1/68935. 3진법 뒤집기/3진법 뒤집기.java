import java.io.*;

class Solution {
    public int solution(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            sb.append(n % 3);
            n /= 3;
        }
        
        String s = sb.toString();
        int ans = 0;
        for(int i = 0; i < s.length(); i++){
            int k = s.charAt(i) - '0';
            if(k == 0) continue;
            
            ans += k * (int) Math.pow(3, s.length() - 1 - i);
        }
        return ans;
    }
}