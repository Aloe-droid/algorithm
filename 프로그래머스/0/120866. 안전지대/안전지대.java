class Solution {
    boolean[][] check;
    int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0};
    int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1};
    
    public int solution(int[][] board) {
        int N = board.length;
        int M = board[0].length;
        
        check = new boolean[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(board[i][j] == 0) continue;
                
                for(int k = 0; k < 8; k++){
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    
                    if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    check[nx][ny] = true;
                }
            }
        }
        
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(!check[i][j] && board[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }
}