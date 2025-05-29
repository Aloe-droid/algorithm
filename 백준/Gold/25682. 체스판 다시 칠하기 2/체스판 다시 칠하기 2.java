import java.io.*;
import java.util.*;

class Main {
    static int N, M, K;
    static char[][] chars;
    static int[][] s1, s2;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        chars = new char[N][M];
        s1 = new int[N][M];
        s2 = new int[N][M];
        
        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                chars[i][j] = s.charAt(j);
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                char c1 = ((i + j) % 2) == 0 ? 'B' : 'W';
                char c2 = ((i + j) % 2) == 0 ? 'W' : 'B';
                if(chars[i][j] != c1) s1[i][j] += 1;
                if(chars[i][j] != c2) s2[i][j] += 1;
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int i1 = i > 0 ? s1[i - 1][j] : 0;
                int i2 = j > 0 ? s1[i][j - 1] : 0;
                int i3 = (i > 0 && j > 0) ? s1[i - 1][j - 1] : 0;
                s1[i][j] += (i1 + i2 - i3);

                i1 = i > 0 ? s2[i - 1][j] : 0;
                i2 = j > 0 ? s2[i][j - 1] : 0;
                i3 = (i > 0 && j > 0) ? s2[i - 1][j - 1] : 0;
                s2[i][j] += (i1 + i2 - i3);                
            }
        }

        int min = Integer.MAX_VALUE;
        for(int x1 = 0; x1 < N; x1++) {
            for(int y1 = 0; y1 < M; y1++) {
                int x2 = x1 + K - 1;
                int y2 = y1 + K - 1;
               if (x2 >= N || y2 >= M) continue;

                int sum = s1[x2][y2];
                int t1 = x1 > 0 ? s1[x1 - 1][y2] : 0;
                int t2 = y1 > 0 ? s1[x2][y1 - 1] : 0;
                int t3 = (x1 > 0 && y1 > 0) ? s1[x1 - 1][y1 - 1] : 0;
                min = Math.min(min, sum - t1 - t2 + t3);

                sum = s2[x2][y2];
                t1 = x1 > 0 ? s2[x1 - 1][y2] : 0;
                t2 = y1 > 0 ? s2[x2][y1 - 1] : 0;
                t3 = (x1 > 0 && y1 > 0) ? s2[x1 - 1][y1 - 1] : 0;
                min = Math.min(min, sum - t1 - t2 + t3);
            }
        }

        System.out.println(min);
    }
}
