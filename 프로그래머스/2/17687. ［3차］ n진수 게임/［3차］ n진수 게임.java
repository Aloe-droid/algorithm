import java.util.*;

class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        
        int k = 0;
        while(sb.length() < t * m){
            sb.append(Integer.toString(k, n));
            k++;
        }
        
        String s = sb.toString();
        sb = new StringBuilder();
        int idx = p - 1;
        while(sb.length() < t){
            sb.append(s.substring(idx, idx + 1));
            idx += m;
        }
        return sb.toString().toUpperCase();
    }
}