import java.io.*;
import java.util.*;

class Main {
    static int N, R, Q;
    static List<List<Integer>> list;
    static int[] ints;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        ints = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
            ints[i] = -1;
        }

        for(int i = 0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.get(x).add(y);
            list.get(y).add(x);
        }

        dfs(R);
        for(int i = 0; i < Q; i++) sb.append(ints[Integer.parseInt(br.readLine())]).append("\n");
        System.out.println(sb);
    }

    public static int dfs(int n){
        if(ints[n] != -1) return ints[n];

        ints[n] = 1;
        for(int k : list.get(n)){
            list.get(k).remove(Integer.valueOf(n));
            ints[n] += dfs(k);
        }

        return ints[n];
    }
}