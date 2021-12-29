package main.java.프로그래머스.거리두기확인하기;

import java.util.*;
class Pair {
    int first;
    int second;
    public Pair(int first,int second) {
        this.first = first;
        this.second = second;
    }
}
public class Solution {
    public int[] solution(String[][] places) {
        int[] answer = {};
        answer = new int[places.length];
        int idx = 0;
        // places 배열 확인
        for (String[] place : places) {
            // 사람 앉아있는 좌표 정보 저장
            ArrayList<Pair> posList = new ArrayList<>();
            for (int i = 0; i < place.length; i++) {
                for (int j = 0; j < place[i].length(); j++) {
                    if (place[i].charAt(j) == 'P')
                        posList.add(new Pair(i, j));
                }
            }
            // 모든 좌표 쌍의 거리 구해서 2 이하인 좌표 쌍이 있으면 무효처리하고 break
            int isValid = 1;
            outer:for (int i = 0; i < posList.size(); i++) {
                for (int j = i + 1; j < posList.size(); j++) {
                    Pair pos1 = posList.get(i);
                    Pair pos2 = posList.get(j);
                    int dist = calculateDist(place, pos1, pos2);
                    if (dist <= 2) {
                        isValid = 0;
                        break outer;
                    }
                }
            }
            answer[idx++] = isValid;
        }
        return answer;
    }
    // bfs로 두 좌표 사이의 거리 계산
    int calculateDist(String[] place, Pair pos1, Pair pos2) {
        int[] dr = {1,0,-1,0};
        int[] dc = {0,1,0,-1};
        int dist = 50;
        boolean[][] isVisit = new boolean[5][5];
        Queue<Pair> queue = new LinkedList<Pair>();
        Queue<Integer> distQ = new LinkedList<Integer>();
        distQ.add(0);
        queue.add(pos1);
        while(!queue.isEmpty()) {
            Pair curPos = queue.poll();
            int curDist = distQ.poll();
            if(curPos.first == pos2.first && curPos.second == pos2.second) {
                dist = curDist;
                break;
            }
            isVisit[curPos.first][curPos.second] = true;
            int nxtr, nxtc;
            for(int i=0; i<4; i++) {
                nxtr = curPos.first + dr[i];
                nxtc = curPos.second + dc[i];
                if(isValid(nxtr,nxtc) && !isVisit[nxtr][nxtc]) {
                    if(place[nxtr].charAt(nxtc) != 'X') {
                        queue.add(new Pair(nxtr, nxtc));
                        distQ.add(curDist + 1);
                    }
                }
            }
        }
        return dist;
    }
    boolean isValid(int r, int c) {
        return 0 <= r && r < 5 && 0 <= c && c < 5;
    }
    // 프로그래머스에서 다른 사람 코드 보고 멋져서 따라해본 코드.. 대박
    // 어떤 칸을 기준으로 그 칸 사방에 사람이 두 명 이상 있으면 ..두 칸 이하로 떨어져 앉은거니깐 ..무효
    public int[] solution2(String[][] places) {
        int[] answer = {};
        answer = new int[places.length];
        int idx = 0;

        int[] dr = {1,0,-1,0};
        int[] dc = {0,1,0,-1};

        for (String[] place : places) {
            int isValid = 1;
            outer:for(int i=0; i<5; i++) {
                for(int j=0; j<5; j++) {
                    int cnt = 0;
                    if(place[i].charAt(j) == 'X')
                        continue;
                    if(place[i].charAt(j) == 'P')
                        cnt ++;
                    for(int k=0; k<4; k++) {
                        int nxtr = i + dr[k];
                        int nxtc = j + dc[k];
                        if (isValid(nxtr, nxtc) && place[nxtr].charAt(nxtc) == 'P') {
                            cnt++;
                        }
                        if(cnt >= 2) {
                            isValid = 0;
                            break outer;
                        }
                    }
                }
            }
            answer[idx++] = isValid;
        }
        return answer;
    }
}
