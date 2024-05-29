import java.util.*;

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int[][] dp = new int[51][onboard.length];
        for(int i = 0; i < dp.length; i++) Arrays.fill(dp[i], 100_000_000);
        temperature += 10;
        t1 += 10;
        t2 += 10;
        
        dp[temperature][0] = 0;
        for(int i = 1; i < onboard.length; i++){
            for(int j = 0; j <= 50; j++){
                if(onboard[i] == 1 && (j < t1 || j > t2)) continue;
                int min = dp[j][i];
                
                // 전원을 끄지 않고 온도를 조절하는 경우
                if(j - 1 >= 0) min = Math.min(min, dp[j - 1][i - 1] + a);
                if(j + 1 <= 50) min = Math.min(min, dp[j + 1][i - 1] + a);
                min = Math.min(dp[j][i - 1] + b, min);
                
                // 전원을 끄는 경우 
                if(temperature == j) min = Math.min(min, dp[j][i -1]);
                if(temperature > j - 1 && j - 1 >= 0) min = Math.min(min, dp[j - 1][i - 1]);
                if(temperature < j + 1 && j + 1 <= 50) min = Math.min(min, dp[j + 1][i - 1]);
                
                dp[j][i] = min;
            }
        }
        
        int last;
        for(last = onboard.length - 1; last >= 0; last--) if(onboard[last] == 1) break;
        
        int min = dp[t1][last];
        for(int i = t1; i <= t2; i++) min = Math.min(min, dp[i][last]);
        
        return min;
    }
}