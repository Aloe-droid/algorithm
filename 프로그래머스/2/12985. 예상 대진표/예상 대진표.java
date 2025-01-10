class Solution{
    public int solution(int n, int a, int b){
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        int cnt = 1;
        
        while(min % 2 != 1 || min + 1 != max) {
            if(min % 2 == 1) min += 1;
            if(max % 2 == 1) max += 1;
            
            min /= 2;
            max /= 2;
            cnt += 1;
        }
        return cnt;
    }
}