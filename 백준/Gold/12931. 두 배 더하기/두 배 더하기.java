import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] mul, sum, ints;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N];
        sum = new int[1001];
        mul = new int[1001];

        for(int i = 1; i <= 1000; i++) {
            int k = i;
            int plus = 0, multiply = 0;
            while(k > 0) {
                if(k % 2 == 1){
                    plus += 1;
                    k -= 1;
                }else {
                    multiply += 1;
                    k /= 2;
                }
            }
            sum[i] = plus;
            mul[i] = multiply;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int mulMax = 0;
        int plus = 0;
        for(int i = 0; i < N; i++) {
            int k = Integer.parseInt(st.nextToken());
            mulMax = Math.max(mul[k], mulMax);
            plus += sum[k];
        }

        System.out.println(mulMax + plus);
    }

}