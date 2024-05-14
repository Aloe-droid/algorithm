import java.util.*;

class Solution {
    static int[][] dp;
    static int X,Y;
    
    public int solution(int alp, int cop, int[][] problems) {
        X = alp;
        Y = cop;
        
        for(int[] problem : problems){
            X = Math.max(X, problem[0]);
            Y = Math.max(Y, problem[1]);
        }
        dp = new int[X + 1][Y + 1];
        
        
        for(int i = alp; i <= X; i++){
            for(int j = cop; j <= Y; j++){
                dp[i][j] = (i - alp) + (j - cop);
            }
        }
        
        
        for(int i = alp; i <= X; i++){
            for(int j = cop; j <= Y; j++){
                
                for(int[] problem : problems){
                    if(problem[0] > i || problem[1] > j) continue;
                    
                    int nx = i + problem[2] < X ? i + problem[2] : X;
                    int ny = j + problem[3] < Y ? j + problem[3] : Y;
                    
                    int temp = dp[i][j] + problem[4];
                    dp[nx][ny] = Math.min(dp[nx][ny], temp);
                }
            }
        }
        return dp[X][Y];
    }
}