import java.io.*;
import java.util.*;

class Main {
    static boolean flag;
    static int N = 6, M = 3;
    static int[][] results, ints;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            results = new int[N][M];
            ints = new int[N][M];
            flag = false;

            for(int n = 0; n < N; n++) {
                for(int m = 0; m < M; m++) {
                    results[n][m] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, 1);
            sb.append(flag ? 1 : 0).append(" ");
        }
        System.out.println(sb);
    }

    public static void dfs(int x, int nx) {
        if(flag) return;

        if(x == N - 1) {
            check();
            return;
        }

        if(nx == N) {
            dfs(x + 1, x + 2);
            return;
        }

        ints[x][0] += 1;
        ints[nx][2] += 1;
        if(!flag) dfs(x, nx + 1);
        ints[x][0] -= 1;
        ints[nx][2] -= 1;

        ints[x][2] += 1;
        ints[nx][0] += 1;
        if(!flag) dfs(x, nx + 1);
        ints[x][2] -= 1;
        ints[nx][0] -= 1;

        ints[x][1] += 1;
        ints[nx][1] += 1;
        if(!flag) dfs(x, nx + 1);
        ints[x][1] -= 1;
        ints[nx][1] -= 1;
    }

    public static void check() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < 3; j++) {
                if(results[i][j] != ints[i][j]) return;
            }
        }

        flag = true;
    }
}