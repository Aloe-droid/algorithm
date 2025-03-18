class Solution {
    int N;
    int[][] minDp, maxDp;
    String[] arr;
    
    public int solution(String[] arr) {
        init(arr);
        for(int len = 2; len <= N; len++) {
            for(int st = 0; st <= N - len; st++) {
                int ed = st + len - 1;
                for(int md = st; md < ed; md++) {
                    operate(st, md, ed);
                }
            }
        }

        return maxDp[0][N - 1];
    }

    public void init(String[] arr) {
        N = (arr.length + 1) / 2;
        this.arr = arr;
        minDp = new int[N][N];
        maxDp = new int[N][N];
        
        int V = 100_000_000;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                maxDp[i][j] = -1 * V;
                minDp[i][j] = V;
            }
        }
        
        for(int i = 0; i < arr.length; i += 2) {
            int v = Integer.parseInt(arr[i]);
            minDp[i / 2][i / 2] = v;
            maxDp[i / 2][i / 2] = v;
        }
    }
    
    public void operate(int st, int md, int ed) {
        String op = arr[md * 2 + 1];
        if(op.equals("+")) {
            maxDp[st][ed] = Math.max(maxDp[st][ed], maxDp[st][md] + maxDp[md + 1][ed]);
            minDp[st][ed] = Math.min(minDp[st][ed], minDp[st][md] + minDp[md + 1][ed]);
        }else{
            maxDp[st][ed] = Math.max(maxDp[st][ed], maxDp[st][md] - minDp[md + 1][ed]);
            minDp[st][ed] = Math.min(minDp[st][ed], minDp[st][md] - maxDp[md + 1][ed]);
        }
    }

}