import java.io.*;
import java.util.*;

class Main {
    static int N;
    static Stack<Integer> st;
    static List<Integer> list;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        st = new Stack<>();
        list = new ArrayList<>();
        
        String[] ss = br.readLine().split(" ");
        for(int i = N - 1; i >= 0; i--) {
            int k = Integer.parseInt(ss[i]);
            
            while(!st.isEmpty() && st.peek() < k) {
                list.add(st.pop());
            }

            st.push(k);
        }

        int count = list.size();
        int max = 0;
        for(int k : list) max = Math.max(max, k);

        while(!st.isEmpty() && st.peek() < max) {
            st.pop();
            count++;
        }
        System.out.println(count);
    }
}