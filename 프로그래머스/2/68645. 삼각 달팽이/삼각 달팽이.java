import java.util.*;
class Solution {
    static int[] dx = {1, 0, -1};
    static int[] dy = {0, 1, -1};
    static int x = 0;
    static int y = 0;
    
    public int[] solution(int n) {
        int[][] ints = new int[n][];
        for(int i = 0; i < n; i++) ints[i] = new int[i + 1];
        int max = (n * (n + 1)) / 2;
        int v = 1;
        int dir = 0;
        ints[0][0] = v++;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n ; j++){
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                
                try{
                    if(ints[nx][ny] != 0) continue;
                    ints[nx][ny] = v++;
                    x = nx;
                    y = ny;
                }catch(Exception e){}
            }    
            
            dir++;
            dir %= 3;
        }
        
        int[] answer = new int[max];
        int idx = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i + 1; j++){
                answer[idx] = ints[i][j];
                idx++;
            }
        }
        
        return answer;
    }
}