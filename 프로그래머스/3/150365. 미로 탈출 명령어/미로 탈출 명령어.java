import java.util.*;

class Solution {
    static int N, M, sX, sY, eX, eY, K;
    static int[] dx = { 1, 0, 0, -1 };
    static int[] dy = { 0, -1, 1, 0 };
    static String ans = "impossible";
    static StringBuilder sb = new StringBuilder();
    static boolean flag = false;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        sX = x - 1;
        sY = y - 1;
        eX = r - 1;
        eY = c - 1;
        K = k;

        int diff = Math.abs(sX - eX) + Math.abs(sY - eY);
        if(diff > K || (K - diff) % 2 != 0) return ans;
        if(diff == 0) return "";
        
        dfs(sX, sY, 0);
        return ans;
    }
    
    public static void dfs(int x, int y, int cnt){
        if(flag) return;
        if(Math.abs(x - eX) + Math.abs(y - eY) > K - cnt) return;
        
        if(x == eX && y == eY && cnt == K){
            ans = sb.toString();
            flag = true;
            return;
        }
        
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx < 0 || nx >= N || ny < 0 || ny >= M || cnt + 1 > K || flag) continue;
            
            sb.append(get(i));
            dfs(nx, ny, cnt + 1);
            sb.deleteCharAt(sb.length() -1);
        }
    }
    
    public static char get(int i){
        if(i == 0) return 'd';
        else if(i == 1) return 'l';
        else if(i == 2) return 'r';
        else return 'u';
    }
}