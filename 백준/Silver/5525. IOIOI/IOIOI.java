import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.readLine();
        String s = br.readLine();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) if (s.charAt(i) == 'I') list.add(i);

        int cnt = 0;
        int temp = 0;
        int prev = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int now = list.get(i);

            if (prev + 2 == now) {
                temp++;
            } else {
                int k = temp - (N - 1);
                if (k > 0) cnt += k;
                temp = 0;
            }

            prev = now;
        }

        int k = temp - (N - 1);
        if (k > 0) cnt += k;

        System.out.println(cnt);
    }
}