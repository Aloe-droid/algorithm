import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static Product[] products;
    static List<List<String>> productLists;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        products = new Product[N + 1];
        productLists = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            products[i] = new Product();
            productLists.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            String[] ss = br.readLine().split(" ");
            int k = Integer.parseInt(ss[ss.length - 1]);
            List<Integer> temp = new ArrayList<>();
            for (int j = 1; j < ss.length - 1; j++) {
                temp.add(Integer.parseInt(ss[j]));
            }

            // N번째 인덱스에 inDegree가 있음
            List<Integer> inDegrees = products[k].inDegrees;
            inDegrees.add(temp.size());
            String key = k + "/" + (inDegrees.size() - 1);
            for (int t : temp) productLists.get(t).add(key);
        }

        int K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        List<Integer> init = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int k = Integer.parseInt(st.nextToken());
            init.add(k);
        }

        print(find(init));
    }

    public static boolean[] find(List<Integer> init) {
        boolean[] check = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>(init);

        while (!q.isEmpty()) {
            int n = q.poll();
            if (check[n]) continue;

            check[n] = true;
            List<String> productIdList = productLists.get(n);
            for (String s : productIdList) {
                StringTokenizer st = new StringTokenizer(s, "/");
                int id = Integer.parseInt(st.nextToken());
                int index = Integer.parseInt(st.nextToken());
                int value = products[id].inDegrees.get(index) - 1;
                products[id].inDegrees.set(index, value);

                if (products[id].inDegrees.get(index) == 0) {
                    q.add(id);
                }
            }
        }

        return check;
    }

    public static void print(boolean[] check) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (!check[i]) continue;
            cnt += 1;
            sb.append(i).append(" ");
        }
        System.out.println(cnt + "\n" + sb);
    }
}

class Product {
    List<Integer> inDegrees;

    public Product() {
        inDegrees = new ArrayList<>();
    }
}
