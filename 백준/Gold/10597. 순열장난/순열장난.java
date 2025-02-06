import java.io.*;
import java.util.*;

class Main {
    static boolean flag;
    static int[] ints;
    static boolean[] visit;
    static String s;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();

        int len = Math.min(s.length(), 9);
        int remain = s.length() - len;
        len += remain / 2;

        ints = new int[len];
        visit = new boolean[len + 1];
        
        flag = false;
        dfs(0, 0);

        StringBuilder sb = new StringBuilder();
        for(int i : ints) sb.append(i).append(" ");
        System.out.println(sb);
    }

    public static void dfs(int charIndex, int index) {
        if(index == ints.length) {
            flag = true;
            return;
        }

        if(s.length() >= charIndex + 1 && !flag) {
            int n1 = Integer.parseInt(s.substring(charIndex, charIndex + 1));
            if(put(n1, index)) {
                dfs(charIndex + 1, index + 1);
                visit[n1] = false;
            }
        }
        
        if(s.length() >= charIndex + 2 && !flag) {
            int n2 = Integer.parseInt(s.substring(charIndex, charIndex + 2));
            if(put(n2, index)) {
                dfs(charIndex + 2, index + 1);
                visit[n2] = false;
            }
        }
    }

    public static boolean put(int n, int idx) {
        if(n > ints.length || n == 0) return false;
        if(visit[n]) return false;

        ints[idx] = n;
        visit[n] = true;
        return true;
    }
}