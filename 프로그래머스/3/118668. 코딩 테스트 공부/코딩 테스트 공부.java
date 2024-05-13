import java.util.*;

class Solution {
    static int minT, maxA, maxC;
    static int[][] problems;
    
    public int solution(int alp, int cop, int[][] problems) throws Exception {
        Solution.problems = problems;
        maxA = alp;
        maxC = cop;
        for(int[] problem : problems){
            maxA = problem[0] > maxA ? problem[0] : maxA;
            maxC = problem[1] > maxC ? problem[1] : maxC;
        }
        
        int diffA = maxA - alp > 0 ? maxA - alp : 0;
        int diffC = maxC - cop > 0 ? maxC - cop : 0;
        
        minT = diffA + diffC;
        for(int i = alp; i <= maxA; i++){
            for(int j = cop; j <= maxC; j++){
                int t = (i - alp) + (j - cop);
                dfs(t, i, j);
            }
        }
        
        return minT;
    }
    
    public static void dfs(int t, int alp, int cop) throws Exception{
        if(t >= minT) return;
        
        if(alp >= maxA && cop >= maxC){
            minT = t;
            return;
        }
        
        for(int i = 0; i < problems.length; i++){
            int needA = problems[i][0] - alp > 0 ? problems[i][0] - alp : 0;
            int needC = problems[i][1] - cop > 0 ? problems[i][1] - cop : 0;
            
            t += needA + needC + problems[i][4];
            alp += needA + problems[i][2];
            cop += needC + problems[i][3];
            
            dfs(t, alp, cop);
            
            t -= needA + needC + problems[i][4];
            alp -= needA + problems[i][2];
            cop -= needC + problems[i][3];
        }
    }
}