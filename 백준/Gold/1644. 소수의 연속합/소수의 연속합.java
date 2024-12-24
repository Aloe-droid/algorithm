import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int sum = 0;
        int cnt = 0;

        for(int i = 2; i <= N; i++){
            if(!isPrime(i)) continue;

            q.add(i);
            sum += i;

            while(sum > N && !q.isEmpty()) {
                sum -= q.poll();
            }

            if(sum == N) cnt++;
        }

        System.out.println(cnt);
    }

    public static boolean isPrime(int n){
        if(n == 2 || n == 3) return true;

        if(n % 2 == 0) return false;
        for(int i = 2; i <= (int) Math.sqrt(n); i++){
            if(n % i == 0) return false;
        }
        return true;
    }
}