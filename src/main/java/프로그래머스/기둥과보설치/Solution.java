package main.java.프로그래머스.기둥과보설치;

public class Solution {
    int[][][] wall;
    int n;
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        wall = new int[n+1][n+1][2];
        this.n = n+1;
        int cnt = 0;
        for(int[] build : build_frame) {
            int y = build[0];
            int x = build[1];
            int a = build[2];
            int cmd = build[3];

            // 설치 or 삭제
            wall[x][y][a] = cmd;
            cnt = cmd==1 ? cnt+1 : cnt-1;

            // 복구
            if(cmd == 1) {
                if(!isRightAdd(x,y,a)) {
                    wall[x][y][a] = 0;
                    cnt--;
                }
            }
            else if(cmd == 0) {
                if(!isRightRemove(x,y,a)) {
                    wall[x][y][a] = 1;
                    cnt++;
                }
            }
        }
        answer = new int[cnt][3];
        int idx = 0;
        for(int i=0;i<this.n;i++) {
            for(int j=0;j<this.n;j++) {
                for(int k=0; k<2; k++)
                    if(wall[j][i][k] == 1)
                        answer[idx++] = new int[]{i,j,k};
            }
        }
        return answer;
    }
    boolean isRightAdd(int x, int y, int a) {
        // 기둥일 경우
        if(a == 0) {
            // 바닥에 세운 경우
            if(x == 0)
                return true;
            //기둥위에 세운 경우
            if(wall[x-1][y][0] == 1)
                return true;
            // 보 위에 세운 경우
            if(wall[x][y][1] == 1 || (y > 0 && wall[x][y-1][1] == 1))
                return true;
        }
        // 보인 경우
        if(a == 1) {
            // 기둥 위에 세운 경우
            if(wall[x-1][y][0] == 1 || (y < n - 1 && wall[x-1][y+1][0] == 1))
                return true;
            // 양쪽 끝이 보인 경우
            if((y > 0 && wall[x][y-1][1] == 1) && (y < n - 1 && wall[x][y+1][1] == 1))
                return true;
        }
        return false;
    }
    boolean isValid(int x,int y){
        return 0<=x && x<n && 0<=y && y<=n;
    }
    boolean isRightRemove(int x,int y,int a) {
        int[] dx = {1,1,-1,-1,1,0,-1,0,0};
        int[] dy = {-1,1,-1,1,0,1,0,-1,0};
        for(int i=0; i<9; i++) {
            int nxtx = x + dx[i];
            int nxty = y + dy[i];
            if(isValid(nxtx,nxty)) {
                if(wall[nxtx][nxty][0] == 1) {
                    if(!isRightAdd(nxtx, nxty, 0))
                        return false;
                }
                if(wall[nxtx][nxty][1] == 1) {
                    if(!isRightAdd(nxtx, nxty, 1))
                        return false;
                }
            }
        }
        return true;
    }
}
