import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skills) {
        int[][] dp = new int[board.length + 2][board[0].length + 2];
        
        for(int[] skill : skills){
            int a = skill[5];
            int b = skill[5] * -1;
            
            if(skill[0] == 1){
                a *= -1;
                b *= -1;
            }
            
            dp[skill[1] + 1][skill[2] + 1] += a;
            dp[skill[3] + 2][skill[4] + 2] += a;
            dp[skill[1] + 1][skill[4] + 2] += b;
            dp[skill[3] + 2][skill[2] + 1] += b;
        }
        
        for(int i = 0; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                dp[i][j] += dp[i][j - 1];
            }
        }
        
       for(int i = 0; i < dp[0].length; i++){
            for(int j = 1; j < dp.length; j++){
                dp[j][i] += dp[j - 1][i];
            }
        }
        
        int cnt = 0;
        for(int i = 1; i < dp.length - 1; i++){
            for(int j = 1; j < dp[0].length - 1; j++){
                board[i - 1][j - 1] += dp[i][j];
                if(board[i - 1][j - 1] > 0) cnt++;
            }
        }
        
        return cnt;
    }
}