import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] in, post, inMap;
    static StringBuilder sb;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        in = new int[N];
        post = new int[N];
        inMap = new int[N + 1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            in[i] = Integer.parseInt(st.nextToken());
            inMap[in[i]] = i;
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) post[i] = Integer.parseInt(st.nextToken());

        sb = new StringBuilder();
        dfs(0, N - 1, 0, N - 1);
        System.out.println(sb);
    }

    public static void dfs(int inL, int inR, int postL, int postR) {
        if(inL > inR || postL > postR) return;

        int root = post[postR];
        sb.append(root).append(" ");

        int rId = inMap[root];
        int leftSize = rId - inL;
        dfs(inL, rId - 1, postL, postL + leftSize - 1);
        dfs(rId + 1, inR, postL + leftSize, postR - 1);
    }
}
