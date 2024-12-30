import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            a *= a;
            b *= b;
            c *= c;

            if(a == 0 && b == 0 && c == 0) break;
            else if(a == b + c || b == a + c || c == a + b) sb.append("right\n");
            else sb.append("wrong\n");
        }
        System.out.println(sb);
    }
}