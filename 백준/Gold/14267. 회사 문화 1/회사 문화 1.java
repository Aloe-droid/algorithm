import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static List<List<Integer>> list;
    static int[] ints, inDegrees;
    static Queue<Integer> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ints = new int[N + 1];
        inDegrees = new int[N + 1];
        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) list.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            int p = Integer.parseInt(st.nextToken());
            if(p == -1) continue;
            list.get(p).add(i);
            inDegrees[i]++;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            ints[n] += w;
        }

        q = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            if(inDegrees[i] == 0) q.add(i);
        }

        while(!q.isEmpty()) {
            int x = q.poll();

            for(int nx : list.get(x)) {
                inDegrees[nx]--;

                if(inDegrees[nx] == 0) {
                    ints[nx] += ints[x];
                    q.add(nx);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) sb.append(ints[i]).append(" ");
        System.out.println(sb);
    }
}
