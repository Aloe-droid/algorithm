import java.io.*;
import java.util.*;

class Main {
    static int N, M, K;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static int[][] ints;
    static int[][][][] visit;
    static Queue<int[]> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ints = new int[N][M];
        visit = new int[N][M][2][K + 1];
        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                ints[i][j] = s.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        q = new LinkedList<>();
        q.add(new int[] {0, 0, 0, 0});
        visit[0][0][0][0] = 1;

        while(!q.isEmpty()) {
            int[] info = q.poll();
            int x = info[0];
            int y = info[1];
            int day = info[2];
            int cnt = info[3];
            if(x == N - 1 && y == M - 1) return visit[x][y][day][cnt];

            for(int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if(day == 0) {
                    if(ints[nx][ny] == 1 && cnt + 1 <= K && visit[nx][ny][1][cnt + 1] == 0) {
                        visit[nx][ny][1][cnt + 1] = visit[x][y][0][cnt] + 1;
                        q.add(new int[] {nx, ny, 1, cnt + 1});
                    }else if(ints[nx][ny] == 0 && visit[nx][ny][1][cnt] == 0) {
                        visit[nx][ny][1][cnt] = visit[x][y][0][cnt] + 1;
                        q.add(new int[] {nx, ny ,1, cnt});
                    }
                }else {
                    if(ints[nx][ny] == 1 || visit[nx][ny][0][cnt] != 0) continue;
                    visit[nx][ny][0][cnt] = visit[x][y][1][cnt] + 1;
                    q.add(new int[] {nx, ny, 0, cnt});
                }
            }

            int nextDay = day == 0 ? 1 : 0;
            if(visit[x][y][nextDay][cnt] != 0) continue;
            visit[x][y][nextDay][cnt] = visit[x][y][day][cnt] + 1;
            q.add(new int[] {x, y, nextDay, cnt});
        }
        return -1;
    }
}
