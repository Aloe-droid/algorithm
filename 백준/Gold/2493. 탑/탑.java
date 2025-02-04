import java.io.*;
import java.util.*;

class Main {
    static int N;
    static Stack<int[]> stack;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        stack = new Stack<>();
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            int v = Integer.parseInt(st.nextToken());

            while(!stack.isEmpty() && stack.peek()[0] < v) stack.pop();

            if(stack.isEmpty()) sb.append("0 ");
            else sb.append(stack.peek()[1]).append(" ");

            stack.add(new int[] {v, i});
        }
        System.out.println(sb);
    }
}