import java.util.*;

class Solution {
    int N, M, H;
    int[][] land;
    boolean[][] visit;
    int[] dx = {-1 ,1, 0, 0}, dy = {0, 0, 1, -1};
    PriorityQueue<int[]> pq;
    
    public int solution(int[][] land, int height) {
        init(land, height);
        return bfs();
    }
    
    public void init(int[][] land, int height) {
        N = land.length;
        M = land[0].length;
        H = height;
        
        this.land = land;
        visit = new boolean[N][M];
        pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[2]));
    }

    public int bfs() {
        int cost = 0;
        pq.add(new int[] {0, 0, 0});
        
        while(!pq.isEmpty()) {
            int x = pq.peek()[0];
            int y = pq.peek()[1];
            int c = pq.poll()[2];
            if(visit[x][y]) continue;

            visit[x][y] = true;
            cost += c;
            for(int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M || visit[nx][ny]) continue;
                
                int nc = Math.abs(land[x][y] - land[nx][ny]);
                if(nc <= H) nc = 0;
                pq.add(new int[] {nx, ny, nc});
            }
        }
        return cost;
    }
}