import java.io.*;
import java.util.*;

class Main {
    static int N, K;
    static int[] ints;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ints = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) ints[i] = Integer.parseInt(st.nextToken());

        int left = 1, right= 10_000_000;
        while(left <= right) {
            int mid = (left + right) / 2;

            if(can(mid)) left = mid + 1;
            else right = mid - 1;
        }
        System.out.println(right);
    }

    public static boolean can(int max){
        int cnt = 0;
        int sum = 0;
        for(int i = 0; i < N; i++) {
            sum += ints[i];

            if(sum >= max) {
                sum = 0;
                cnt += 1;
            }
        }

        return cnt >= K;
    }
}
