import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int max = 3;
    
    public int[] solution(String[][] places) {
        int[] ans = new int[places.length];
        for(int i = 0; i < places.length; i++) if(check(places[i])) ans[i] = 1;
        return ans;
    }
    
    public boolean check(String[] ss){
        for(int i = 0; i < ss.length; i++){
            for(int j = 0; j < ss[i].length(); j++){
                char c = ss[i].charAt(j);
                if(c == 'O' || c == 'X') continue;
                boolean result = bfs(i, j, ss);
                if(result) return false;
            }
        }
        
        return true;
    }
    
    public boolean bfs(int i, int j, String[] ss){
        Queue<int[]> q = new LinkedList<>();
        int[][] check = new int[5][5];
        check[i][j] = 1;
        q.add(new int[] {i, j});
        
        while(!q.isEmpty()){
            int x = q.peek()[0];
            int y = q.poll()[1];
            
            for(int k = 0; k < 4; k++){
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || check[nx][ny] != 0) continue;
                if(Math.abs(nx - i) + Math.abs(ny - j) >= max) continue;
                
                char c1 = ss[x].charAt(y);
                char c2 = ss[nx].charAt(ny);
                    
                if(c1 == 'P' && c2 == 'P') return true;
                if(c1 == 'O' && c2 == 'P') return true;
                
                check[nx][ny] = check[x][y] + 1;
                q.add(new int[] {nx, ny});
            }
        }

        return false;
    }
}