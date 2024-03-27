import java.io.*;
import java.util.*;
 
class Solution {
    static int MAX = 100_000_000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[][] ints = new int[N][N];
            for (int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    ints[i][j] = Integer.parseInt(st.nextToken());
                    if (i != j && ints[i][j] == 0) ints[i][j] = MAX;
                }
            }
             
            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        ints[i][j] = Math.min(ints[i][j], ints[i][k] + ints[k][j]);
                    }
                }
            }
             
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) min = Math.min(min, Arrays.stream(ints[i]).sum());
            sb.append("#").append(tc).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }
}