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
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        Set<Integer> ts = new TreeSet<>();
        int[] ints = new int[n];

        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(st.nextToken());
            ints[i] = num;
            ts.add(num);
        }

        int idx = 0;
        Map<Integer, Integer> hm = new HashMap<>();
        for(int i : ts){
            hm.put(i, idx);
            idx++;
        }

        for(int i : ints) bw.write(hm.get(i) + " ");


        bw.flush();
        bw.close();
        br.close();

    }
}