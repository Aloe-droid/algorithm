import java.util.*;

public class Solution {
    static int k, n, ans;
    static List<List<Person>> list;
    static int[][] time;

    public int solution(int k, int n, int[][] reqs) {
        ans = 0;
        Solution.k = k;
        Solution.n = n;
        list = new ArrayList<>();
        time = new int[k + 1][n - k + 2];
        for (int i = 0; i <= k; i++) list.add(new ArrayList<>());
        for (int[] req : reqs) list.get(req[2]).add(new Person(req[0], req[1]));
        init();
        find();

        return ans;
    }

    public void init() {
        for (int i = 1; i <= k; i++) {
            List<Person> lp = list.get(i);

            for (int j = 1; j <= n - k + 1; j++) {
                PriorityQueue<Integer> pq = new PriorityQueue<>();
                int sum = 0;
                for (Person p : lp) {
                    if (pq.size() < j) pq.add(p.s + p.t);
                    else {
                        int prev = pq.poll();
                        int diff = prev - p.s;

                        if (diff > 0) {
                            sum += diff;
                            pq.add(prev + p.t);
                        } else {
                            pq.add(p.s + p.t);
                        }
                    }
                }
                time[i][j] = sum;
            }
        }
    }

    public static void find() {
        int[] index = new int[k + 1];
        Arrays.fill(index, 1);

        for (int i = 0; i < n - k; i++) {
            int maxDiff = 0;
            int maxIdx = -1;

            for (int j = 1; j <= k; j++) {
                int idx = index[j];
                if (idx + 1 < time[j].length) {
                    int diff = time[j][idx] - time[j][idx + 1];
                    if (diff > maxDiff) {
                        maxDiff = diff;
                        maxIdx = j;
                    }
                }
            }

            if (maxIdx != -1) {
                index[maxIdx]++;
            }
        }

        int sum = 0;
        for(int i = 1; i <= k; i++){
            sum += time[i][index[i]];
        }
        ans = sum;
    }
}

class Person {
    int s;
    int t;

    Person(int s, int t) {
        this.s = s;
        this.t = t;
    }

    @Override
    public String toString() {
        return "s: " + s + ", t: " + t;
    }
}