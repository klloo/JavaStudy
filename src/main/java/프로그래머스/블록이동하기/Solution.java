package main.java.프로그래머스.블록이동하기;

import java.util.*;

class Pair {
    int r;
    int c;
    public Pair(int r,int c) {
        this.r = r;
        this.c = c;
    }
}
class Robot {
    Pair head;
    Pair tail;
    int dist;
    public Robot(Pair head, Pair tail,int dist) {
        this.head = head;
        this.tail = tail;
        this.dist = dist;
    }
}
public class Solution {
    int n;
    int[][] board;
    public int solution(int[][] board) {
        int answer = 10000000;
        // 도착점 좌표
        n = board.length;
        this.board = board;
        boolean[][][] visited = new boolean[n][n][4];

        Queue<Robot> queue = new LinkedList<>();
        Robot robot = new Robot(new Pair(0,1),new Pair(0,0),0);
        visited[0][1][3] = true;
        queue.add(robot);

        // 0 상 1 우 2 하 3 좌
        int[] dr = {-1,0,1,0};
        int[] dc = {0,1,0,-1};

        while(!queue.isEmpty()) {
            Robot curRobot = queue.poll();
            Pair head = curRobot.head;
            Pair tail = curRobot.tail;
            int dist = curRobot.dist;

            // 머리 꼬리 둘 중 하나 도착하면 정답 갱신
            if((head.r==n-1&&head.c==n-1)||(tail.r==n-1&&tail.c==n-1)) {
                answer = dist;
                break;
            }

            // 회전
            for(int rt=1;rt<4;rt++) {
                // 머리 기준 회전
                Pair center = head;
                int tailDir = calDir(center, tail);
                for(int i=0;i<=1; i++) {
                    Pair nxtTail = rotate(center, tailDir, rt,i);
                    if(nxtTail != null) {
                        if(!visited[head.r][head.c][calDir(head,nxtTail)] && !visited[nxtTail.r][nxtTail.c][calDir(nxtTail,head)]) {
                            visited[head.r][head.c][calDir(head,nxtTail)] = true;
                            visited[nxtTail.r][nxtTail.c][calDir(nxtTail,head)] = true;
                            queue.add(new Robot(head, nxtTail, dist + rt));
                        }
                    }
                }

                // 꼬리 기준 회전
                center = tail;
                int headDir = calDir(center, head);
                for(int i=0;i<=1; i++) {
                    Pair nxtHead = rotate(center, headDir, rt,i);
                    if (nxtHead != null) {
                        if (!visited[nxtHead.r][nxtHead.c][calDir(nxtHead,tail)] && !visited[tail.r][tail.c][calDir(tail,nxtHead)]) {
                            visited[nxtHead.r][nxtHead.c][calDir(nxtHead,tail)] = true;
                            visited[tail.r][tail.c][calDir(tail,nxtHead)] = true;
                            queue.add(new Robot(nxtHead, tail,dist + rt));
                        }
                    }
                }
            }
            // 이동
            for(int i=0; i<4; i++) {
                int nxtHr = head.r + dr[i];
                int nxtHc = head.c + dc[i];
                int nxtTr = tail.r + dr[i];
                int nxtTc = tail.c + dc[i];
                Pair newHead = new Pair(nxtHr,nxtHc);
                Pair newTail = new Pair(nxtTr,nxtTc);
                if(isValid(nxtHr,nxtHc) && isValid(nxtTr,nxtTc)) {
                    if(board[nxtHr][nxtHc] == 0 && board[nxtTr][nxtTc] == 0) {
                        if(!visited[nxtHr][nxtHc][calDir(newHead,newTail)] && !visited[nxtTr][nxtTc][calDir(newTail,newHead)]) {
                            visited[nxtHr][nxtHc][calDir(newHead,newTail)] = true;
                            visited[nxtTr][nxtTc][calDir(newTail,newHead)] = true;
                            queue.add(new Robot(newHead,newTail,dist+1));
                        }
                    }
                }
            }
        }
        return answer;
    }
    boolean isValid(int r,int c) {
        return 0<=r&&r<n&&0<=c&&c<n;
    }
    // ceter를 기준으로 dir 방향으로 되어있는 로봇을 rt번 회전시킨 결과 위치 반환
    // 회전 못시킬 경우 null 리턴
    // dir = 상 우 하 좌
    Pair rotate(Pair center, int dir, int rt, int rd) {
        int[] dr = {-1,0,1,0};
        int[] dc = {0,1,0,-1};

        int nxtDir = (dir + rt) % 4;
        // 반시계인 경우
        if(rd == 0)
            nxtDir = (dir - rt + 4) % 4;
        int nxtr = center.r+dr[nxtDir];
        int nxtc = center.c+dc[nxtDir];

        if(!isValid(nxtr,nxtc))
            return null;
        if(board[nxtr][nxtc] == 1)
            return null;

        int[] cdr = {-1,-1,1,1};
        int[] cdc = {-1,1,1,-1};

        for(int i=1;i<=rt;i++) {
            int nxtcdir = (dir+i)%4;
            // 반시계인 경우
            if(rd == 0)
                nxtcdir = (5+dir-i)%4;
            int cornerR = center.r + cdr[nxtcdir];
            int cornerC = center.c + cdc[nxtcdir];
            if(!isValid(cornerR,cornerC))
                return null;
            if(board[cornerR][cornerC] == 1)
                return null;
        }

        Pair nxtPos = new Pair(nxtr, nxtc);
        return nxtPos;
    }
    // center를 기준으로 edge의 dir을 반환해주는 함수
    // 0 상 1 우 2 하 3 좌
    int calDir(Pair center, Pair edge) {
        int dir = 0;
        // 같은 행일 경우
        if(center.r == edge.r) {
            if(center.c < edge.c)
                dir = 1;
            else
                dir = 3;
        }
        else if(center.c == edge.c) {
            if(center.r < edge.r)
                dir = 2;
            else
                dir = 0;
        }
        return dir;
    }
}
