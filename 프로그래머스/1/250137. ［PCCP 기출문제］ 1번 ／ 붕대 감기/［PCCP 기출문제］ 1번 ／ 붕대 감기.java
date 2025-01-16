class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxH = health;
        int maxT = 0;
        int idx = 0;
        int inc = 0;
        boolean flag = true;
        for(int[] a : attacks) maxT = Math.max(maxT, a[0]);
        for(int t = 1; t <= maxT; t++) {
            if(attacks[idx][0] > t) {
                inc++;
                health += bandage[1];
                if(inc == bandage[0]) {
                    health += bandage[2];
                    inc = 0;
                }
                health = Math.min(health, maxH);
                continue;
            }
            
            health -= attacks[idx++][1];
            inc = 0;
            if(health <= 0) {
                flag = false;
                break;
            }
        }
        
        return flag ? health : -1;
    }
}