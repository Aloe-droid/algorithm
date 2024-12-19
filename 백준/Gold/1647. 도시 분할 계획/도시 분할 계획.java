import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static List<List<Road>> list;
    static int[] mst;
    static PriorityQueue<Road> pq;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>(Comparator.comparingInt(r -> r.weight));
        list = new ArrayList<>();
        mst = new int[N + 1];

        for(int i = 0; i <= N; i++){
            list.add(new ArrayList<>());
            mst[i] = -1;
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            list.get(x).add(new Road(y, z));
            list.get(y).add(new Road(x, z));
        }

        int x = find();
        System.out.println(split(x));
    }

    public static int find(){
        pq.add(new Road(1, 0));

        while(!pq.isEmpty()){
            Road road = pq.poll();
            if(mst[road.node] != -1) continue;
            mst[road.node] = road.weight;

            for(Road next : list.get(road.node)){
                if(mst[next.node] != -1) continue;
                pq.add(next);
            }
        }

        int max = -1;
        int maxIdx = -1;
        for(int i = 1; i <= N; i++){
            if(mst[i] > max) {
                max = mst[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public static int split(int x){
        Arrays.fill(mst, -1);
        pq.add(new Road(1, 0));
        pq.add(new Road(x, 0));

        while(!pq.isEmpty()){
            Road road = pq.poll();
            if(mst[road.node] != -1) continue;
            mst[road.node] = road.weight;

            for(Road next : list.get(road.node)){
                if(mst[next.node] != -1) continue;
                pq.add(next);
            }
        }

        int sum = 0;
        for(int i = 1; i <= N; i++) sum += mst[i];
        return sum;
    }
}

class Road {
    int node, weight;
    public Road(int node, int weight){
        this.node = node;
        this.weight = weight;
    }
}