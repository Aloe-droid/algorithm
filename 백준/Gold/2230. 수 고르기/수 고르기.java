import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static long[] longs;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        longs = new long[N];
        for(int i = 0; i < N; i++) longs[i] = Long.parseLong(br.readLine());
        Arrays.sort(longs);

        int left = 0;
        int right = left + 1;
        long min = Long.MAX_VALUE;
        while(left < N && right < N) {
            long diff = longs[right] - longs[left];
            if(diff >= M) min = Math.min(min, diff);

            if(diff > M) left += 1;
            else if(diff < M) right += 1;
            else break;
        }
        System.out.println(min);
    }
}