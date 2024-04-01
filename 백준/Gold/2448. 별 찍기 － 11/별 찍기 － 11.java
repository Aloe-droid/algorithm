import java.io.*;
import java.util.*;

class Main {
    static int N;
    static char[][] chars;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        chars = new char[N][N * 2];
        for (char[] aChar : chars) Arrays.fill(aChar, ' ');
        dfs(0, N - 1, N);

        for (char[] aChar : chars) {
            for (int j = 0; j < chars[0].length; j++) bw.write(aChar[j] + "");
            bw.write("\n");
        }
        
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int i, int j, int k) {
        if (k == 3) {
            chars[i][j] = '*';
            chars[i + 1][j + 1] = '*';
            chars[i + 1][j - 1] = '*';
            for (int idx = -2; idx <= 2; idx++) chars[i + 2][j + idx] = '*';
            return;
        }

        dfs(i, j, k / 2);
        dfs(i + k / 2, j + k / 2, k / 2);
        dfs(i + k / 2, j - k / 2, k / 2);
    }

}