import java.io.*;
import java.util.*;

class Main {
    static int N, M, max = 1_000_000;
    static Station[] stations;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init(br.readLine().split(" "));

        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            String task = st.nextToken();
            int i = 0, j = 0;
            
            if(task.equals("BN")) {
                i = Integer.parseInt(st.nextToken()); 
                j = Integer.parseInt(st.nextToken());
                nextAdd(i, j, sb);
            }else if(task.equals("BP")) {
                i = Integer.parseInt(st.nextToken());
                j = Integer.parseInt(st.nextToken());
                prevAdd(i, j, sb);
            }else if(task.equals("CN")) {
                i = Integer.parseInt(st.nextToken());
                nextRemove(i, sb);
            }else if(task.equals("CP")) {
                i = Integer.parseInt(st.nextToken());
                prevRemove(i, sb);
            }
        }
        // print();
        System.out.println(sb);
    }

    public static void nextAdd(int i, int j, StringBuilder sb) {
        sb.append(stations[i].next.idx).append("\n");

        if(stations[j] != null) return;
        stations[j] = new Station(j);

        Station st = stations[i];
        Station next = stations[i].next;

        st.next = stations[j];
        next.prev = stations[j];

        stations[j].prev = st;
        stations[j].next = next;
    }

    public static void prevAdd(int i, int j, StringBuilder sb) {
        sb.append(stations[i].prev.idx).append("\n");

        if(stations[j] != null) return;
        stations[j] = new Station(j);

        Station st = stations[i];
        Station prev = stations[i].prev;

        st.prev = stations[j];
        prev.next = stations[j];

        stations[j].next = st;
        stations[j].prev = prev;
    }

    public static void nextRemove(int i, StringBuilder sb) {
        sb.append(stations[i].next.idx).append("\n");

        Station st = stations[i];
        Station next = stations[i].next;
        Station nnext = stations[next.idx].next;

        st.next = nnext;
        nnext.prev = st;

        if(stations[0].next.idx == next.idx) stations[0].next = nnext;
        stations[next.idx] = null;
    }

    public static void prevRemove(int i, StringBuilder sb) {
        sb.append(stations[i].prev.idx).append("\n");

        Station st = stations[i];
        Station prev = stations[i].prev;
        Station pprev = stations[prev.idx].prev;

        st.prev = pprev;
        pprev.next = st;

        if(stations[0].next.idx == prev.idx) stations[0].next = pprev;
        stations[prev.idx] = null;
    }
    
    public static void init(String[] strings) {
        int[] stationArr = new int[strings.length];
        for(int i = 0; i < strings.length; i++) {
            stationArr[i] = Integer.parseInt(strings[i]);
        }
        
        stations = new Station[max + 1];
        stations[0] = new Station(0);

        for(int i = 0; i < N; i++) {
            int id = stationArr[i];
            stations[id] = new Station(id);
        }

        for(int i = 0; i < N; i++) {
            int id = stationArr[i];
            int next = stationArr[(i + 1) % N];
            int prev = i != 0 ? stationArr[i - 1] : stationArr[N - 1];
            
            stations[id].next = stations[next];
            stations[next].prev = stations[id];

            stations[id].prev = stations[prev];
            stations[prev].next = stations[id];
        }

        stations[0].next = stations[stationArr[0]];
    }

    public static void print() {
        boolean[] visit = new boolean[max + 1];
        int id = 0;
        while(true) {
            Station s = stations[id].next;
            if(s == null || visit[s.idx]) break;
            System.out.println("id: " + s.idx + ", next: " + s.next.idx + ", prev: " + s.prev.idx);
            visit[s.idx] = true; 
            id = s.idx;
        }
    }
}

class Station {
    int idx;
    Station next, prev;

    public Station(int idx){
        this.idx = idx;
    }
}