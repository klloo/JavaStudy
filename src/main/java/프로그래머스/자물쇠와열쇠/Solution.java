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
    List<Pair> apertureList = new ArrayList<>();
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        m = key.length;
        n = lock.length;
        initKey(key);
        this.lock = lock;
        for(int i=0; i<n; i++) {
            for(int j=0;j<n;j++){
                if(lock[i][j] == 0)
                    apertureList.add(new Pair(i,j));
            }
        }
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
            setKey(originalKey);
            rotate();
        }
        return answer;
    }
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
    void rotate() {
        int[][] temp = cloneKey();
        for(int i=0; i<m; i++) {
            for(int j=0; j<m; j++) {
                key[i][j] = temp[m-1-j][i];
            }
        }
    }
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
    int[][] cloneKey() {
        int[][] temp = new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                temp[i][j] = key[i][j];
            }
        }
        return temp;
    }
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
    void setKey(int[][] temp) {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                key[i][j] = temp[i][j];
            }
        }
    }
}
