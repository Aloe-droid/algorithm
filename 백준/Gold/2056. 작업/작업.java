import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] time, inDegrees, sum;
    static List<List<Integer>> list;
    static LinkedList<Integer> q;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        time = new int[N + 1];
        inDegrees = new int[N + 1];
        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) list.add(new ArrayList<>());

        for(int i = 1 ; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            time[i] = t;
            
            int J = Integer.parseInt(st.nextToken());
            for(int j = 0; j < J; j++) {
                int in = Integer.parseInt(st.nextToken());
                inDegrees[i]++;
                list.get(in).add(i);
            }
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        sum = new int[N + 1];
        q = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            if(inDegrees[i] == 0) {
                q.add(i);
                sum[i] = time[i];
            }
        }

        int max = 0;
        while(!q.isEmpty()) {
            int x = q.poll();
            max = Math.max(max, sum[x]);

            for(int nx : list.get(x)) {
                inDegrees[nx]--;
                sum[nx] = Math.max(sum[nx], sum[x] + time[nx]);
                if(inDegrees[nx] == 0) q.add(nx);
            }
        }
        return max;
    }
}
