import java.util.*;

class Solution {
    public int solution(int[] sc, int[][] tl, int s) {
        for(int i = 0; i < sc.length; i++) sc[i] = convert(sc[i]);
        for(int i = 0; i < tl.length; i++) for(int j = 0; j < 7; j++) tl[i][j] = convert(tl[i][j]);
        boolean[] can = new boolean[sc.length];
        Arrays.fill(can, true);
        
        for(int i = s; i < s + 7; i++) {
            int day = i % 7;
            if(day == 0 || day == 6) continue;
           
            for(int j = 0; j < sc.length; j++) {
                if(!can[j]) continue;
                
                int scd = sc[j];
                int t = tl[j][i - s];
                if(scd + 10 < t) can[j] = false;
            }
        }
        
        int cnt = 0;
        for(boolean c : can) {
            if(c) cnt++;
        }
       
        return cnt;
    }
    
    public int convert(int t) {
        int hour = t / 100;
        int min = t % 100;
        return hour * 60 + min;
    }
}