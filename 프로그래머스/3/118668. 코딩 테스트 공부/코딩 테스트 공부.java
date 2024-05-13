import java.util.*;

class Solution {
    static int maxX, maxY;
    static int[][] maps;
    static Queue<int[]> q;
    static int[][] problems;
    
    public int solution(int alp, int cop, int[][] problems) {
        maxX = alp;
        maxY = cop;
        Solution.problems = problems;
        
        // 가로: 알고력, 세로: 코딩력
        for(int[] problem : problems){
            if(problem[0] > maxX) maxX = problem[0];
            if(problem[1] > maxY) maxY = problem[1];
        }
        
        maps = new int[maxX + 1][maxY + 1];
        init(alp, cop);
        
        
        for(int i = alp; i <= maxX; i++){
            for(int j = cop; j <= maxY; j++){
                bfs(i, j);
            }
        }
        return maps[maxX][maxY];
    }
    
    public static void init(int x, int y){
        for(int i = x; i <= maxX; i++){
            for(int j = y; j <= maxY; j++){
                maps[i][j] = Math.abs(i - x) + Math.abs(j - y);
            }
        }
    }
    
    public static void bfs(int x, int y){
        q = new LinkedList<>();
        q.add(new int[] {x, y});
        
        while(!q.isEmpty()){
            int px = q.peek()[0];
            int py = q.poll()[1];
            
            for(int[] problem : problems){
                int nx = px + problem[2];
                int ny = py + problem[3];
                
                if(nx > maxX) nx = maxX;
                if(ny > maxY) ny = maxY;
                
                int alpha = 0;
                if(px < problem[0]) alpha += problem[0] - px;
                if(py < problem[1]) alpha += problem[1] - py;
                alpha += problem[4];
                
                if(maps[nx][ny] > maps[px][py] + alpha){
                    maps[nx][ny] = maps[px][py] + alpha;
                    q.add(new int[] {nx, ny});
                }
            }
        }
    }
}