import java.io.*;
import java.util.*;

class Main {
    static int N, M, max = 1_000_000_000;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] ints;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ints = new int[N][M];
        dp = new int[N][M][2];

        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                ints[i][j] = s.charAt(j) - '0';
                Arrays.fill(dp[i][j], max);
            }
        }

        bfs();

        if(dp[N - 1][M - 1][0] == max && dp[N - 1][M - 1][1] == max) System.out.println(-1);
        else System.out.println(Math.min(dp[N - 1][M - 1][0], dp[N - 1][M - 1][1]));
    }

    public static void bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {0, 0, 0});
        dp[0][0][0] = 1;

        while(!q.isEmpty()){
            int x = q.peek()[0];
            int y = q.peek()[1];
            int z = q.poll()[2];

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                // 벽을 뚫고 가는 경우
                if(ints[nx][ny] == 1 && z == 0 && dp[x][y][z] + 1 < dp[nx][ny][1]){
                    dp[nx][ny][1] = dp[x][y][z] + 1;
                    q.add(new int[] {nx, ny, 1});
                }
                    
                // 이미 벽을 뚫어서 기회가 없는 경우
                if(ints[nx][ny] == 0 && z == 1 && dp[x][y][z] + 1 < dp[nx][ny][z]){
                    dp[nx][ny][z] = dp[x][y][z] + 1;
                    q.add(new int[] {nx, ny, z});
                }

                // 벽을 뚫지도 않고, 다음도 벽이 아닌 경우
                if(ints[nx][ny] == 0 && z == 0 && dp[x][y][z] + 1 < dp[nx][ny][z]){
                    dp[nx][ny][z] = dp[x][y][z] + 1;
                    q.add(new int[] {nx, ny, z});
                }
            }
        }
    }
}