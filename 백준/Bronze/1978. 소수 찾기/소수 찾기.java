import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] p = new boolean[1001];
        for(int i = 2; i <= 1000; i++) {
            if(isPrime(i)){
                p[i] = true;
                for(int j = i * i; j <= 1000; j *= 2) p[j] = false;
            }
        }

        int cnt = 0;
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            if(p[Integer.parseInt(st.nextToken())]) cnt++;
        }
        System.out.println(cnt);
    }

    public static boolean isPrime(int k) {
        for(int i = 2; i <= (int) Math.sqrt(k); i++) if(k % i == 0) return false;
        return true;
    }
}