import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static Product[] products;
    static List<Set<Integer>> productLists;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        products = new Product[N + 1];
        productLists = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            products[i] = new Product();
            productLists.add(new HashSet<>());
        }

        for (int i = 0; i < M; i++) {
            String[] ss = br.readLine().split(" ");
            int k = Integer.parseInt(ss[ss.length - 1]);
            Set<Integer> hs = new HashSet<>();
            for (int j = 1; j < ss.length - 1; j++) {
                hs.add(Integer.parseInt(ss[j]));
            }
            products[k].needs.add(hs);
            for (int kk : hs) productLists.get(kk).add(k);
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
        Queue<Integer> q = new LinkedList<>();
        for (int i : init) {
            check[i] = true;
            q.add(i);
        }

        while (!q.isEmpty()) {
            int n = q.poll();
            check[n] = true;
            Set<Integer> productIdList = productLists.get(n);
            for (int id : productIdList) {
                Product product = products[id];
                for (Set<Integer> need : product.needs) {
                    need.remove(n);
                    if (need.isEmpty()) {
                        products[id].needs.clear();
                        q.add(id);
                        break;
                    }
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
    List<Set<Integer>> needs;

    public Product() {
        needs = new ArrayList<>();
    }
}
