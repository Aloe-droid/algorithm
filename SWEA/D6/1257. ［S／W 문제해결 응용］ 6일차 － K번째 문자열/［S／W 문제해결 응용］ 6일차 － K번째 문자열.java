import java.io.*;
import java.util.*;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine()) - 1;
            String s = br.readLine();
            TreeSet<String> ts = new TreeSet<>();

            for (int i = 0; i < s.length(); i++) {
                for (int j = i + 1; j < s.length() + 1; j++) {
                    ts.add(s.substring(i, j));
                }
            }

            String[] strings = ts.toArray(new String[0]);
            bw.write("#" + tc + " " + (strings.length > N ? strings[N] + "\n" : "none\n"));
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
