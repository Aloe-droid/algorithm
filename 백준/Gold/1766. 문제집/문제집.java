import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static List<List<Integer>> list;
    static int[] inDegrees;
    static PriorityQueue<Integer> pq;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inDegrees = new int[N + 1];
        list = new ArrayList<>();
        for(int i = 0; i <= N; i++) list.add(new ArrayList<>());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.get(x).add(y);
            inDegrees[y]++;
        }

        System.out.println(find());
    }

    public static String find() {
        StringBuilder sb = new StringBuilder();
        pq = new PriorityQueue<>();
        for(int i = 1; i <= N; i++) if(inDegrees[i] == 0) pq.add(i);

        while(!pq.isEmpty()) {
            int x = pq.poll();
            sb.append(x).append(" ");

            for(int nx : list.get(x)) {
                if(--inDegrees[nx] == 0) pq.add(nx);
            }
        }


        return sb.toString();
    }
}
