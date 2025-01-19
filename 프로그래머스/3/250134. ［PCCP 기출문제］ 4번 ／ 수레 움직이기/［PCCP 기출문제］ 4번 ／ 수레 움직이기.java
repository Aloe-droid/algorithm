import java.util.*;

class Solution {
    static int N, M, min = Integer.MAX_VALUE;
    static int[][] maze, visit;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static int[] dirR, dirB, dstR, dstB; 
    
    public int solution(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        Solution.maze = maze;
        visit = new int[N][M];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(maze[i][j] == 1) dirR = new int[] {i, j};
                else if(maze[i][j] == 2) dirB = new int[] {i, j};
                else if(maze[i][j] == 3) dstR = new int[] {i, j};
                else if(maze[i][j] == 4) dstB = new int[] {i, j};
                visit[i][j] = 1;
            }
        }
        
        visit[dirR[0]][dirR[1]] *= 3;
        visit[dirB[0]][dirB[1]] *= 2;
        dfs(0);
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    
    public static void dfs(int c) {
        if(c >= min) return;
        if(check()) {
            min = c;
            return;
        }
        
        // visit 논리 : 초기값 1
        // 2 -> 파란 수레가 지나감, 3 -> 빨간 수레가 지나감, 6 -> 두 수레 모두 지나감
        
        if(!canRed()) {
            for(int i = 0; i < 4; i++) {
                int nx = dirR[0] + dx[i];
                int ny = dirR[1] + dy[i];
                
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(maze[nx][ny] == 5 || (nx == dirB[0] && ny == dirB[1])) continue;
                if(visit[nx][ny] != 1 && visit[nx][ny] % 3 == 0) continue;
                
                visit[nx][ny] *= 3;
                dirR[0] += dx[i];
                dirR[1] += dy[i];
                
                moveBlue(c);
                
                visit[nx][ny] /= 3;
                dirR[0] -= dx[i];
                dirR[1] -= dy[i];
            }
        }
        
        if(!canBlue()) {
            for(int i = 0; i < 4; i++) {
                int nx = dirB[0] + dx[i];
                int ny = dirB[1] + dy[i];
                
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(maze[nx][ny] == 5 || (nx == dirR[0] && ny == dirR[1])) continue;
                if(visit[nx][ny] != 1 && visit[nx][ny] % 2 == 0) continue;
                
                visit[nx][ny] *= 2;
                dirB[0] += dx[i];
                dirB[1] += dy[i];
                
                moveRed(c);
                
                visit[nx][ny] /= 2;
                dirB[0] -= dx[i];
                dirB[1] -= dy[i];
            }
        }
    }
    
    public static void moveRed(int c) {
        if(!canRed()) {
            for(int j = 0; j < 4; j++) {
                int nnx = dirR[0] + dx[j];
                int nny = dirR[1] + dy[j];
                
                if(nnx < 0 || nnx >= N || nny < 0 || nny >= M) continue;
                if(maze[nnx][nny] == 5 || (nnx == dirB[0] && nny == dirB[1])) continue;
                if(visit[nnx][nny] != 1 && visit[nnx][nny] % 3 == 0) continue;
                        
                visit[nnx][nny] *= 3;
                dirR[0] += dx[j];
                dirR[1] += dy[j];
                        
                dfs(c + 1);
                        
                visit[nnx][nny] /= 3;
                dirR[0] -= dx[j];
                dirR[1] -= dy[j];
            }
        }else {
            dfs(c + 1);
        }
    }
    
    public static void moveBlue(int c) {
        if(!canBlue()) {
            for(int j = 0; j < 4; j++) {
                int nnx = dirB[0] + dx[j];
                int nny = dirB[1] + dy[j];
                        
                if(nnx < 0 || nnx >= N || nny < 0 || nny >= M) continue;
                if(maze[nnx][nny] == 5 || (nnx == dirR[0] && nny == dirR[1])) continue;
                if(visit[nnx][nny] != 1 && visit[nnx][nny] % 2 == 0) continue;
                        
                visit[nnx][nny] *= 2;
                dirB[0] += dx[j];
                dirB[1] += dy[j];
                        
                dfs(c + 1);
                        
                visit[nnx][nny] /= 2;
                dirB[0] -= dx[j];
                dirB[1] -= dy[j];
            }
        }else {
            dfs(c + 1);
        }
    }
    
    public static boolean check() {
        return canRed() && canBlue();
    }
    
    
    public static boolean canRed() {
        return dirR[0] == dstR[0] && dirR[1] == dstR[1];
    }
    
    public static boolean canBlue() {
        return dirB[0] == dstB[0] && dirB[1] == dstB[1];
    }
}