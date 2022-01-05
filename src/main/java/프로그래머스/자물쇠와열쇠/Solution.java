package main.java.프로그래머스.자물쇠와열쇠;

import java.util.*;

class Pair {
    int r;
    int c;
    public Pair(int r,int c) {
        this.r = r;
        this.c = c;
    }
}
public class Solution {
    int n;
    int m;
    int[][] key;
    int[][] lock;
    // 자물쇠의 홈 부분을 저장하는 배열
    List<Pair> apertureList = new ArrayList<>();
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        m = key.length;
        n = lock.length;
        // 키와 자물쇠를 초기화한다.
        initKey(key);
        this.lock = lock;
        // 자물쇠의 홈 부분을 저장한다.
        for(int i=0; i<n; i++) {
            for(int j=0;j<n;j++){
                if(lock[i][j] == 0)
                    apertureList.add(new Pair(i,j));
            }
        }
        // 시계방향으로 4번 회전하고, 한 회전당 상하좌우 이동할 수 있는 모든 조합을 이동해본다.
        outer:for(int i=0;i<4;i++) {
            int[][] originalKey = cloneKey();
            for(int j=0; j<n; j++) {
                slide('D',j, originalKey);
                int[][] downKey = cloneKey();
                for(int k=0; k<n; k++) {
                    slide('R',k, downKey);
                    if(isRight()) {
                        answer = true;
                        break outer;
                    }
                }
                for(int k=0; k<m; k++) {
                    slide('L', k, downKey);
                    if (isRight()) {
                        answer = true;
                        break outer;
                    }
                }
            }
            for(int j=0;j<m;j++) {
                slide('U',j, originalKey);
                int[][] upKey = cloneKey();
                for(int k=0; k<n; k++) {
                    slide('R',k, upKey);
                    if(isRight()) {
                        answer = true;
                        break outer;
                    }
                }
                for(int k=0; k<m; k++) {
                    slide('L',k, upKey);
                    if(isRight()) {
                        answer = true;
                        break outer;
                    }
                }
            }
            // 키를 원상태로 돌려준다. (회전만 한 상태)
            setKey(originalKey);
            rotate();
        }
        return answer;
    }
    // 키를 dir 방향으로 step 만큼 밀어낸다.
    void slide(char dir, int step, int[][] temp) {
        if(dir == 'D') {
            for(int i=step; i<n; i++)
                for(int j=0; j<n; j++)
                    key[i][j] = temp[i-step][j];
            for(int i=0;i<step;i++)
                for(int j=0; j<n; j++)
                    key[i][j] = -1;
        }
        else if(dir == 'R') {
            for(int i=0; i<n; i++)
                for(int j=step; j<n; j++)
                    key[i][j] = temp[i][j-step];
            for(int i=0;i<n;i++)
                for(int j=0;j<step;j++)
                    key[i][j] = -1;
        }
        else if(dir == 'U') {
            for(int i=step; i<n; i++)
                for(int j=0; j<n; j++)
                    key[i-step][j] = temp[i][j];
            for(int i=0;i<step;i++)
                for(int j=0; j<n; j++)
                    key[n-1-i][j] = -1;
        }
        else if(dir == 'L') {
            for(int i=0; i<n; i++)
                for(int j=step; j<n; j++)
                    key[i][j-step] = temp[i][j];
            for(int i=0;i<n;i++)
                for(int j=0;j<step;j++)
                    key[i][n-1-j] = -1;
        }
    }
    // 키를 시계 방향으로 회전
    void rotate() {
        int[][] temp = cloneKey();
        for(int i=0; i<m; i++) {
            for(int j=0; j<m; j++) {
                key[i][j] = temp[m-1-j][i];
            }
        }
    }
    // 키로 자물쇠를 열 수 있는지 확인
    boolean isRight() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(key[i][j] == lock[i][j])
                    return false;
            }
        }
        for(Pair pair :apertureList) {
            if(key[pair.r][pair.c] != 1)
                return false;
        }
        return true;
    }
    // 현재 키의 상태를 복사해서 반환
    int[][] cloneKey() {
        int[][] temp = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                temp[i][j] = key[i][j];
            }
        }
        return temp;
    }
    // 자물쇠와 동일한 크기로 키를 초기화
    void initKey(int[][] key) {
        this.key = new int[n][n];
        for(int i=0;i<n;i++)
            Arrays.fill(this.key[i],-1);
        for(int i=0; i<m; i++) {
            for(int j=0; j<m; j++) {
                this.key[i][j] = key[i][j];
            }
        }
    }
    // temp 와 동일한 상태로 키 설정
    void setKey(int[][] temp) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                key[i][j] = temp[i][j];
            }
        }
    }
}
