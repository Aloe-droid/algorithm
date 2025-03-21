import java.util.*;

class Solution {
    int N, M;
    int[] dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };
    char[][] map;
    boolean[][] visit;
    Queue<int[]> q;
    
    public int solution(String[] storage, String[] requests) {
        init(storage);
        for(String req : requests) {
            if(req.length() == 1) bfs(req.charAt(0));
            else delete(req.charAt(0));
        }
        
        int cnt = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                char c = map[i][j];
                if(c >= 'A' && c <= 'Z') cnt++;
            }
        }
        return cnt;
    }
    
    public void init(String[] storage) {
        N = storage.length + 2;
        M = storage[0].length() + 2;
        
        map = new char[N][M];
        for(int i = 0; i < N - 2; i++) {
            for(int j = 0; j < M - 2; j++) {
                map[i + 1][j + 1] = storage[i].charAt(j);
            }
        }
    }
    
    public void bfs(char c) {
        visit = new boolean[N][M];
        q = new LinkedList<>();
        q.add(new int[] { 0, 0 });
        visit[0][0] = true;
        
        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.poll()[1];
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M || visit[nx][ny]) continue;
                
                visit[nx][ny] = true;
                if(map[nx][ny] == c) map[nx][ny] = map[0][0];
                else if(map[nx][ny] == map[0][0]) q.add(new int[] {nx, ny});
            }
        }
    }
    
    public void delete(char c) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != c) continue;
                map[i][j] = map[0][0];
            }
        }
    }
}