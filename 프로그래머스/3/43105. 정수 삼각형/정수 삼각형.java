import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int[][] dp = new int[triangle.length][];
        for(int i = 0; i < triangle.length; i++) dp[i] = new int[triangle[i].length];
        dp[0][0] = triangle[0][0];
        
        for(int i = 1; i < dp.length; i++){
            for(int j = 0; j < dp[i].length; j++){
                int k = 0;
                try{ k = Math.max(k, dp[i-1][j]); }catch(Exception e){ }
                try{ k = Math.max(k, dp[i-1][j -1 ]); }catch(Exception e){ }
                dp[i][j] = k + triangle[i][j];
            }
        }
        return Arrays.stream(dp[dp.length -1]).max().getAsInt();
    }
}