import java.util.*;

class Solution {
    static int M, N;
    static int[][] ints;
    static boolean[][] visit;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static Queue<int[]> q;
    
    public int[] solution(int m, int n, int[][] picture) {
        M = m;
        N = n;
        ints = picture;
        visit = new boolean[M][N];
        q = new LinkedList<>();
        
        return find();
    }
    
    public static int[] find() {
        int cnt = 0;
        int maxArea = 0;
        
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(ints[i][j] == 0 || visit[i][j]) continue;
                
                cnt++;
                int area = bfs(i, j);
                maxArea = Math.max(maxArea, area);
            }
        }
        return new int[] {cnt, maxArea};
    }
    
    public static int bfs(int i, int j) {
        int area = 0;
        q.add(new int[] {i, j});
        visit[i][j] = true;
        
        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.poll()[1];
            area += 1;
            
            for(int n = 0; n < 4; n++) {
                int nx = x + dx[n];
                int ny = y + dy[n];
                
                if(nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
                if(visit[nx][ny] || ints[nx][ny] != ints[x][y]) continue;
                
                q.add(new int[] {nx, ny});
                visit[nx][ny] = true;
            }
        }
        return area;
    }
}