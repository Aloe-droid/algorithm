import java.io.*;
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] ints = new int[10_001];
        for(int i = 0; i < N; i++) ints[Integer.parseInt(br.readLine())]++;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= 10000; i++) {
            if(ints[i] == 0) continue;
            for(int j = 0; j < ints[i]; j++) sb.append(i).append("\n");
        }
        System.out.println(sb);
    }
}