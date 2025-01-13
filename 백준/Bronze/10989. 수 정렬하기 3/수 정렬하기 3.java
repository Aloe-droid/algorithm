import java.io.*;
import java.util.*;
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ints = new int[N];
        for(int i = 0; i < N; i++) ints[i] = Integer.parseInt(br.readLine());
        Arrays.sort(ints);
        StringBuilder sb = new StringBuilder();
        for(int k : ints) sb.append(k).append("\n");
        System.out.println(sb);
    }
}