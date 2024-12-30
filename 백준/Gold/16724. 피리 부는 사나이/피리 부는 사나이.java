import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static int[] p;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        p = new int[N * M];
        for(int i = 0; i < N * M; i++) p[i] = i;

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                char c = s.charAt(j);
                union(i * M + j, getD(i, j, c));
            }
        }

        for(int i = 0; i < N * M; i++) find(i);
        Set<Integer> hs = new HashSet<>();
        for(int i = 0; i < N * M; i++) hs.add(p[i]);
        System.out.println(hs.size());
    }

    public static int find(int k) {
        if(p[k] == k) return k;
        return p[k] = find(p[k]);
    }

    public static void union(int x, int y){
        int pX = find(x);
        int pY = find(y);
        p[pX] = pY;
    }

    public static int getD(int x, int y, char dir){
        if(dir == 'L' && y != 0) y--;
        else if(dir == 'R' && y != M - 1) y++;
        else if(dir == 'U' && x != 0) x--;
        else if(dir == 'D' && x != N - 1) x++;

        return x * M + y;
    }
}