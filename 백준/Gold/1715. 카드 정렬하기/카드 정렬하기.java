import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            long l = Long.parseLong(br.readLine());
            pq.add(l);
        }

        long ans = 0L;
        while(pq.size() >= 2) {
            long v1 = pq.poll();
            long v2 = pq.poll();
            pq.add(v1 + v2);
            ans += v1 + v2;
        }
        System.out.println(ans);
    }
}
