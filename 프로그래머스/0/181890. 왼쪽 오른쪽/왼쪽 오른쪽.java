class Solution {
    public String[] solution(String[] strArr) {
        int idx = -1;
        String val = "";
        for(int i = 0; i < strArr.length; i++){
            if(strArr[i].equals("l") || strArr[i].equals("r")){
                idx = i;
                val = strArr[i];
                break;
            }
        }
        
        if(val.equals("l")){
            String[] ans = new String[idx];
            for(int i = 0; i < idx; i++) ans[i] = strArr[i];
            return ans;
        }else if(val.equals("r")){
            String[] ans = new String[strArr.length - idx - 1];
            for(int i = idx + 1; i < strArr.length; i++) ans[i - idx - 1] = strArr[i];
            return ans;
        }else{
            return new String[] {};
        }
    }
}