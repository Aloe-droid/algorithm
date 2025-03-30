import java.io.*;

class Main {
    static int N;
    static int[] ints;
    static boolean[][][][][] dp;
    static char[] chars;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        N = s.length();
        ints = new int[3];
        for(int i = 0; i < s.length(); i++) ints[s.charAt(i) - 'A']++;
        dp = new boolean[N + 1][N + 1][N + 1][3][3];
        chars = new char[N];
        boolean flag = dfs(0, 0, 0, 0, 0);
        System.out.println(flag ? new String(chars) : -1);
    }

    public static boolean dfs(int a, int b, int c, int pp, int p) {
        if(a == ints[0] && b == ints[1] && c == ints[2]) return true;
        if(dp[a][b][c][pp][p]) return false;

        dp[a][b][c][pp][p] = true;

        if(a < ints[0]) {
            chars[a + b + c] = 'A';
            if(dfs(a + 1, b, c, p, 0)) return true;
        }

        if(b < ints[1] && p != 1) {
            chars[a + b + c] = 'B';
            if(dfs(a, b + 1, c, p, 1)) return true;
        }

        if(c < ints[2] && pp != 2 && p != 2) {
            chars[a + b + c] = 'C';
            if(dfs(a, b, c + 1, p, 2)) return true;
        }

        return false;
    }
}
