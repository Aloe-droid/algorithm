import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] ints, max;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ints = new int[N];
        for(int i = 0; i < N; i++) ints[i] = Integer.parseInt(st.nextToken());

        max = new int[N + 1];
        int len = 0;
        for(int k : ints) {
            if(max[len] < k) {
                max[++len] = k;
                continue;
            }

            int left = 0, right = len;
            while(left <= right) {
                int mid = (left + right) / 2;
                if(max[mid] < k) left = mid + 1;
                else right = mid - 1;
            }
            max[left] = k;
        }
        System.out.println(len);
    }
}