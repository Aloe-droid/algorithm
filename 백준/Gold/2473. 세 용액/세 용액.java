import java.io.*;
import java.util.*;

class Main {
    static int N, i1, i2, i3;
    static long min = Long.MAX_VALUE;
    static long[] longs;
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        longs = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) longs[i] = Long.parseLong(st.nextToken());
        Arrays.sort(longs);
        find();
        System.out.println(longs[i1] + " " + longs[i2] + " " + longs[i3]);
    }

    public static void find(){
        for(int i = 0; i < N - 2; i++){
            long bias = longs[i];
            int left = i + 1;
            int right = longs.length - 1;

            while(left < right) {
                long value = bias + longs[left] + longs[right];
                if(Math.abs(value) < min){
                    min = Math.abs(value);
                    i1 = i;
                    i2 = left;
                    i3 = right;
                }

                if(value >= 0) right--;
                else left++;
            }
        }
    }
}