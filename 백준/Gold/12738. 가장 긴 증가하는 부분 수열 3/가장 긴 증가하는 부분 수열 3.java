import java.io.*;
import java.util.*;

class Main {
    static int N, len = 0;
    static int[] ints;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N + 1];
        ints[0] = Integer.MIN_VALUE;
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int v = Integer.parseInt(st.nextToken());

            if(ints[len] < v) {
                ints[++len] = v;
                continue;
            }
            
            int left = 0, right = len;
            while(left <= right) {
                int mid = (left + right) / 2;
                
                if(ints[mid] < v) left = mid + 1;
                else right = mid - 1;
            }

            ints[left] = v;
        }

        System.out.println(len);
    }
}