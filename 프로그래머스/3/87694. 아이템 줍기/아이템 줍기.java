import java.util.*;

class Solution {
    static int N = 101;
    static int[][] ints;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    public int solution(int[][] rectangle, int sX, int sY, int eX, int eY) {
        init(rectangle);
        bfs(sX * 2, sY * 2, eX * 2, eY * 2);
        return (ints[eX * 2][eY * 2] - 1) / 2;
    }
    
    public static void bfs(int sX, int sY, int eX, int eY){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {sX, sY});
        
        while(!q.isEmpty()){
            int x = q.peek()[0];
            int y = q.poll()[1];
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N || ints[nx][ny] != 1) continue;
                
                q.add(new int[] {nx, ny});
                ints[nx][ny] = ints[x][y] + 1;
            }
        }
    }
    
    public static void init(int[][] rectangle){
        ints = new int[N][N];
        for(int[] rect : rectangle){
            int sX = rect[0] * 2;
            int sY = rect[1] * 2;
            int eX = rect[2] * 2;
            int eY = rect[3] * 2;
            
            for(int i = sX; i <= eX; i++){
                for(int j = sY; j <= eY; j++){
                    if(ints[i][j] == -1) continue;
                    
                    if(i == sX || i == eX || j == sY || j == eY) ints[i][j] = 1;
                    else ints[i][j] = -1;
                }
            }
        }
    }
}