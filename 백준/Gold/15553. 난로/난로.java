import java.io.*;
import java.util.*;

class Main {
    static int N, K;
    static int[] ints;
    static PriorityQueue<Integer> pq;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ss = br.readLine().split(" ");
        N = Integer.parseInt(ss[0]);
        K = Integer.parseInt(ss[1]);
        pq = new PriorityQueue<>(Comparator.reverseOrder());
        ints = new int[N];

        for(int i = 0; i < N; i++) ints[i] = Integer.parseInt(br.readLine());
        
        int cnt = 1;
        for(int i = 0; i < N - 1; i++) {
            int diff = ints[i + 1] - ints[i];
            if(diff == 1) cnt += 1;
            else pq.add(diff);
        }

        for(int i = 0; i < K - 1; i++) {
            if(pq.size() == 0) break;

            pq.poll();
            cnt += 1;
        }

        while(!pq.isEmpty()) cnt += pq.poll();
        System.out.println(cnt);
    }
}