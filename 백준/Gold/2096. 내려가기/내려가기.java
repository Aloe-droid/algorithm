import java.io.*;
import java.util.*;
class Main {
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] ints = new int[N][3];
        int[][] dp1 = new int[N][3];
        int[][] dp2 = new int[N][3];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                ints[i][j] = Integer.parseInt(st.nextToken());
                if(i == 0) {
                    dp1[i][j] = ints[i][j];
                    dp2[i][j] = ints[i][j];
                }
            }
        }

        for(int i = 1; i < N; i++) {
            int[] maxArr = new int[3];
            maxArr[0] = Math.max(dp1[i - 1][0], dp1[i - 1][1]);
            maxArr[2] = Math.max(dp1[i - 1][1], dp1[i - 1][2]);
            maxArr[1] = Math.max(maxArr[0], maxArr[2]);

            int[] minArr = new int[3];
            minArr[0] = Math.min(dp2[i - 1][0], dp2[i - 1][1]);
            minArr[2] = Math.min(dp2[i - 1][1], dp2[i - 1][2]);
            minArr[1] = Math.min(minArr[0], minArr[2]);

            for(int j = 0; j < 3; j++) {
                dp1[i][j] = maxArr[j] + ints[i][j];
                dp2[i][j] = minArr[j] + ints[i][j];
            }
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int i = 0; i < 3; i ++) {
            max = Math.max(max, dp1[N - 1][i]);
            min = Math.min(min, dp2[N - 1][i]);
        }

        System.out.println(max + " " + min);
    }
}