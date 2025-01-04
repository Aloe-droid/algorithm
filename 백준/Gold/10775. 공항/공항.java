import java.io.*;
class Main {
    static int G, P;
    static int[] dir;
    static boolean[] use;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        dir = new int[G + 1];
        use = new boolean[G + 1];
        for(int i = 1; i <= G; i++) dir[i] = i;

        int cnt = 0;
        for(int i = 0; i < P; i++) {
            int k = Integer.parseInt(br.readLine());
            if(dfs(k) == 0) break;
            cnt += 1;
        }
        System.out.println(cnt);
    }

    public static int dfs(int k) {
        int d = dir[k];
        if(d == 0) return 0;

        if(!use[d]) {
            use[d] = true;
            dir[k] = dir[d - 1];
            return d;
        }

        return dir[d] = dfs(dir[d - 1]);
    }
}