import java.util.*;

class Solution {
    static int[] times;
    static Queue<Bus> bus;
    
    public String solution(int n, int t, int m, String[] timetable) {
        init(n, t, m, timetable);
        int time = find();
        String hour = Integer.toString(time / 60);
        String minute = Integer.toString(time % 60);
        if(hour.length() == 1) hour = "0" + hour;
        if(minute.length() == 1) minute = "0" + minute;
        return hour + ":" + minute;
    }
    
    public static void init(int n, int t, int m, String[] timetable) {    
        times = new int[60 * 24];
        bus = new LinkedList<>();
        
        for(int i = 0; i < n; i++){
            int time = 9 * 60 + i * t;
            bus.add(new Bus(time, m));
        }
        
        for(String str : timetable){
            StringTokenizer st = new StringTokenizer(str, ":");
            int hour = Integer.parseInt(st.nextToken());
            int minute = Integer.parseInt(st.nextToken());
            int time = hour * 60 + minute;
            times[time]++;
        }
    }
    
    public static int find(){
        for(int i = 0; i < times.length; i++){
            // 해당 시간에 온 승객
            int human = times[i];
            
            // 승객을 태울 수 있는 버스가 있는지 확인
            while(!bus.isEmpty() && human != 0){
                Bus peek = bus.peek();
                
                int max = Math.min(peek.seat, human);
                peek.seat -= max;
                human -= max;
                
                if(peek.seat == 0) bus.poll();
            }
            
            // 버스를 다 못 태우면 1분전의 시간에 서있어야 함.
            if(bus.isEmpty()){
                int time = i - 1;
                if(time < 0) return 0;
                else return time;
            }
            
            // 버스는 해당 시간이 지나면 여유 좌석이 있어도 떠나야 함.
            if(!bus.isEmpty() && bus.peek().time == i){
                bus.poll();
            }
        }
        return 0;
    }
}

class Bus{
    int time, seat;
    public Bus(int time, int seat){
        this.time = time;
        this.seat = seat;
    }
}