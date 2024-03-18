import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        List<Integer> al = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            al.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(al);

        for (int i : al) {
            bw.write(i + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}