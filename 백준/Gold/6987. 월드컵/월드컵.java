import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static boolean flag;
    static int N = 6, M = 3;
    static int[][] results;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            results = new int[N][M];
            flag = false;

            boolean temp = false;
            for(int n = 0; n < N; n++) {
                int sum = 0;
                for(int m = 0; m < M; m++) {
                    results[n][m] = Integer.parseInt(st.nextToken());
                    sum += results[n][m];
                }

                if(sum != 5) {
                    temp = true;
                    break;
                }
            }

            if(temp) {
                sb.append("0 ");
                continue;
            }
            
            dfs(0, 1);
            sb.append(flag ? 1 : 0).append(" ");
        }
        System.out.println(sb);
    }

    public static void dfs(int x, int nx) {
        if(flag) return;
        if(x == N - 1) {
            flag = true;
            return;
        }

        if(nx == N) {
            dfs(x + 1, x + 2);
            return;
        }

        if(results[x][0] > 0 && results[nx][2] > 0) {
            results[x][0] -= 1;
            results[nx][2] -= 1;
            if(!flag) dfs(x, nx + 1);
            results[x][0] += 1;
            results[nx][2] += 1;
        }

        if(results[x][2] > 0 && results[nx][0] > 0) {
            results[x][2] -= 1;
            results[nx][0] -= 1;
            if(!flag) dfs(x, nx + 1);
            results[x][2] += 1;
            results[nx][0] += 1;
        }

        if(results[x][1] > 0 && results[nx][1] > 0 ) {
            results[x][1] -= 1;
            results[nx][1] -= 1;
            if(!flag) dfs(x, nx + 1);
            results[x][1] += 1;
            results[nx][1] += 1;
        }
    }

}