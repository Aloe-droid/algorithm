import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        List<Integer> list = new ArrayList<>();
        List<Double> area = new ArrayList<>();
        
        while(k > 1){
            list.add(k);
            if(k % 2 == 0) k /= 2;
            else k = k * 3 + 1;
        }
        list.add(1);
        
        for(int i = 0; i < list.size() - 1; i++){
            int min = Math.min(list.get(i), list.get(i + 1));
            int max = Math.max(list.get(i), list.get(i + 1));
            area.add(min + ((max - min) / 2.0));
        }
        
        double[] answer = new double[ranges.length];
        for(int i = 0; i < ranges.length; i++){
            double sum = 0.0;
            int start = ranges[i][0];
            int end = list.size() + ranges[i][1] - 1;
            
            if(start > end){
                answer[i] = -1;
                continue;
            }
            
            for(int j = start; j < end; j++) sum += area.get(j);
            answer[i] = sum;
        }
        
        return answer;
    }
}