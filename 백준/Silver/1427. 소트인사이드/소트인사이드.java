import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = br.readLine();

        int[] ints = new int[s.length()];
        for(int i = 0; i < s.length(); i++) ints[i] = s.charAt(i) - '0';
        Arrays.sort(ints);
        for(int i = ints.length - 1; i >=0; i--) sb.append(ints[i]);
        System.out.println(sb);
    }
}
