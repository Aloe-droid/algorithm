import java.io.*;
import java.util.*;

class Main {
    static int R, C, max = 1;
    static char[][] chars;
    static int[] cnt, dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        chars = new char[R][C];
        cnt = new int['Z' - 'A' + 1];
        for(int i = 0; i < R; i++) {
            String s = br.readLine();
            for(int j = 0; j < C; j++) chars[i][j] = s.charAt(j);
        }

        int init = chars[0][0] - 'A';
        cnt[init]++;
        dfs(0, 0);
        System.out.println(max);
    }

    public static void dfs(int x, int y) {
        check();

        for(int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
            int value = chars[nx][ny] - 'A';
            if(cnt[value] != 0) continue;

            cnt[value]++;
            dfs(nx, ny);
            cnt[value]--;
        }
    }

    public static void check() {
        int count = 0;
        for(int i : cnt) if(i != 0) count++;
        max = Math.max(max, count);
    }
}