import java.io.*;
import java.util.*;

class Main {
    static int N = 9;
    static int[][] ints;
    static boolean flag = false;
    static List<int[]> list;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        ints = new int[N][N];
        list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                ints[i][j] = Integer.parseInt(st.nextToken());
                if(ints[i][j] == 0) list.add(new int[] {i, j});
            }
        }

        dfs(0);
        for(int[] ii : ints) {
            for(int i : ii) sb.append(i).append(" ");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void dfs(int idx) {
        if(idx == list.size()) {
            flag = true;
            return;
        }

        int x = list.get(idx)[0];
        int y = list.get(idx)[1];
        boolean[] check = new boolean[10];

        for(int i = 0; i < 9; i++) {
            check[ints[x][i]] = true;
            check[ints[i][y]] = true;
        }

        int sx = (x / 3) * 3;
        int sy = (y / 3) * 3;
        for(int i = 0 ; i  < 3; i++) {
            for(int j = 0; j < 3; j++) {
                check[ints[sx + i][sy + j]] = true;
            }
        }

        for(int i = 1; i <= 9; i++) {
            if(check[i] || flag) continue;
            
            ints[x][y] = i;
            dfs(idx + 1);
            if(flag) continue;
            ints[x][y] = 0;
        }
    }
}