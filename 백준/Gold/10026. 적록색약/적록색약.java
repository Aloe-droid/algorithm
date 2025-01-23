import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[][] ints;
    static boolean[][] visit;
    static Queue<int[]> q;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N][N];
        visit = new boolean[N][N];
        q = new LinkedList<>();
        
        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < N; j++) {
                char c = s.charAt(j);
                if(c == 'R') ints[i][j] = 0;
                else if(c == 'G') ints[i][j] = 1;
                else ints[i][j] = 2;
            }
        }

        int c1 = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visit[i][j]) continue;
                bfs(i, j, ints[i][j], false);
                c1++;
            }
        }

        int c2 = 0;
        visit = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visit[i][j]) continue;
                bfs(i, j, ints[i][j], true);
                c2++;
            }
        }

        System.out.println(c1 + " " + c2);
    }

    public static void bfs(int sX, int sY, int st, boolean flag) {
        visit[sX][sY] = true;
        q.add(new int[] {sX, sY});

        while(!q.isEmpty()) {
            int x = q.peek()[0];
            int y = q.poll()[1];

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visit[nx][ny]) continue;
                if(!flag && ints[nx][ny] != st) continue;
                if(flag && ints[nx][ny] != st && (st == 2 || ints[nx][ny] == 2)) continue;
                
                visit[nx][ny] = true;
                q.add(new int[] {nx, ny});
            }
        }
    }
}