import java.io.*;
import java.util.*;

class Solution {
    static int N, M, max = 0;
    static int[][] ints;
    static boolean[][] check;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static Queue<int[]> q;
    static List<Set<Oil>> list;
    
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        ints = land;
        list = new ArrayList<>();
        for(int i = 0; i < M; i++) list.add(new HashSet<>());
        
        find();
        check();
        return max;
    }
    
    public static void find() {
        check = new boolean[N][M];
        q = new LinkedList<>();
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(ints[i][j] == 0 || check[i][j]) continue;
                bfs(i, j, new Oil());
            }
        }
    }
    
    public static void bfs(int i, int j, Oil o) {
        check[i][j] = true;
        q.add(new int[] {i, j});
        o.cnt++;
        list.get(j).add(o);
        
        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.poll()[1];
            
            for(int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(ints[nx][ny]  == 0 || check[nx][ny]) continue;
                
                check[nx][ny] = true;
                q.add(new int[] {nx, ny});
                o.cnt++;
                list.get(ny).add(o);
            }
        }
    }
    
    public static void check() {
        int cnt = 0;
        for(Set<Oil> hs : list) {
            int temp = 0;
            for(Oil o : hs) temp += o.cnt;
            cnt = Math.max(cnt, temp);
        }
        max = cnt;
    }
}

class Oil {
    int cnt;
    
    public Oil() {
        int cnt = 0;
    }
}