import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int[][] dp = new int[triangle.length][];
        for(int i = 0; i < dp.length; i++) dp[i] = new int[triangle[i].length];
        dp[0][0] = triangle[0][0];
        
    
        for(int i = 1; i < dp.length; i++){
            for(int j = 0; j < dp[i].length; j++){
                int max = 0;
                if(j - 1 >= 0) max = Math.max(max, dp[i - 1][j - 1]);
                if(j != dp[i].length -1) max = Math.max(max, dp[i - 1][j]);
                
                dp[i][j] = max + triangle[i][j];
            }            
        }
        
        int max = 0;
        for(int k : dp[dp.length - 1]) if(k > max) max = k;
        return max;
    }
}