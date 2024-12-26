import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ss = br.readLine().split(" ");
        int N = ss.length;
        int[][][] dp = new int[5][5][N + 1];
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], 1_000_000_000);
            }
        }

        dp[0][0][0] = 0;
        for(int k = 1; k <= N; k++){
            int dst = Integer.parseInt(ss[k - 1]);
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    if(dst != j) dp[dst][j][k] = Math.min(dp[dst][j][k], dp[i][j][k - 1] + getC(i, dst));
                    if(dst != i) dp[i][dst][k] = Math.min(dp[i][dst][k], dp[i][j][k - 1] + getC(j, dst));
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                min = Math.min(min, dp[i][j][N - 1]);
            }
        }
        System.out.println(min);
    }

    public static int getC(int x, int y) {
        if(x == y) return 1;
        else if(x == 0 || y == 0) return 2;
        else if(Math.abs(x - y) == 2) return 4;
        else return 3;
    }
}
