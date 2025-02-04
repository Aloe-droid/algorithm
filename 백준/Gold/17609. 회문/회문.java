import java.io.*;
import java.util.*;

class Main {
    static String s;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            s = br.readLine();
            sb.append(dfs(0, s.length() - 1, 0)).append("\n");
        }
        System.out.println(sb);
    }

    public static int dfs(int left, int right, int corr) {
        if(corr >= 2) return 2;
        if(left > right) return corr;

        char c1 = s.charAt(left);
        char c2 = s.charAt(right);
        
        int v = 2;
        if(c1 == c2) {
            v = Math.min(v, dfs(left + 1, right - 1, corr));
        }else{
            int v1 = dfs(left + 1, right, corr + 1);
            int v2 = dfs(left, right - 1, corr + 1);
            v = Math.min(Math.min(v1, v2), v);
        }
        return v;
    }
}