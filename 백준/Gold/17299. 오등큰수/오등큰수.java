import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[] ints, sum, ans;
    static Stack<Integer> st;
    static StringBuilder sb;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ints = new int[N];
        ans = new int[N];
        sum = new int[1_000_001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
            sum[ints[i]]++;
        }

        popAll();
        print();
    }

    public static void popAll() {
        st = new Stack<>();
        
        for(int i = N - 1; i >= 0; i--) {
            int kv = sum[ints[i]];

            boolean flag = false;
            while(!st.isEmpty()) {
                int pv = sum[st.peek()];

                if(kv >= pv) st.pop();
                else {
                    flag = true;
                    break;
                }
            }

            ans[i] = flag ? st.peek() : -1;
            st.add(ints[i]);
        }
    }

    public static void print() {
        StringBuilder sb = new StringBuilder();
        for(int a : ans) sb.append(a).append(" ");
        System.out.println(sb);
    }
}