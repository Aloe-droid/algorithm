import java.io.*;
import java.util.*;

class Main {
    static long A, B, ans = -1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        dfs(A, 1);
        System.out.println(ans);
    }

    public static void dfs(long n, int cnt){
        if(n >= B){
            if(n == B) ans = cnt;
            return;
        }

        dfs(n * 2, cnt + 1);
        if(ans != -1) return;
        dfs(n * 10 + 1, cnt + 1);
    }
}