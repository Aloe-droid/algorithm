import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        Set<Integer> hs = new HashSet<>();
        Set<Integer> temp = new HashSet<>();
        int N = cards.length + 1;
        for (int i = 0; i < cards.length / 3; i++) hs.add(cards[i]);
        int start = N / 3;
        int end = start + 2;
        
        // 초기에 되는 경우 
        for(int i : hs){
            if(hs.contains(N - i)){
                temp.add(i);
                temp.add(N - i);
                end += 1;
            }
        }
        hs.removeAll(temp);
        temp.clear();
        
        // 탐색하면서 찾는 과정
        while(end <= cards.length){
            boolean flag = false;
            for(int i = start; i < end; i++) temp.add(cards[i]);
            
            if(coin > 0){
                for(int i : hs){
                    if(temp.contains(N - i)){
                        flag = true;
                        coin--;
                        hs.remove(i);
                        temp.remove(N - i);
                        start = end;
                        end += 2;
                        break;
                    }
                }
            }
            
            if(flag) continue;
            
            if(coin >= 2){
                for(int i : temp){
                    if(temp.contains(N - i)){
                        temp.remove(i);
                        temp.remove(N - i);
                        coin -= 2;
                        start = end;
                        end += 2;
                        flag = true;
                        break;
                    }
                }
            }
            if(!flag) break;
        }
        return (end - N / 3) / 2;
    }
}