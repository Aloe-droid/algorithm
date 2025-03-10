import java.io.*;
import java.util.*;

class Main {
    static int N, C;
    static int[] ints;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ints = new int[N];
        for(int i = 0; i < N; i++) ints[i] = Integer.parseInt(br.readLine());
        Arrays.sort(ints);
        System.out.println(search());
    }

    public static int search() {
        int left = 1, right = 1_000_000_000;

        while(left <= right) {
            int mid = (left + right) / 2;
            
            if(check(mid)) left = mid + 1;
            else right = mid - 1;
        }

        return right;
    }

    public static boolean check(int v) {
        int cnt = 1;
        int prev = ints[0];
        for(int i = 1; i < N; i++) {
            if(ints[i] - prev < v) continue;
            cnt++;
            prev = ints[i];
        }

        return cnt >= C; 
    }
}