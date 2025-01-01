import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ss = br.readLine().split(" ");
        long l = 0L;
        for(String s : ss) {
            l += (long) Math.pow(Integer.parseInt(s), 2);
        }
        System.out.println(l % 10);
    }
}