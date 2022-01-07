package main.java.프로그래머스.외벽점검;

import java.util.*;

public class Solution {
    int[] weak;
    int[] dist;
    int wn;
    boolean visit[];
    List<Integer> friends;
    int ans = 10;
    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;
        friends = new ArrayList<>();
        wn = weak.length;
        this.dist = dist;

        // weak 배열을 두배로 늘려준다
        this.weak = new int[wn*2];
        int len = weak.length;
        for(int i=0;i<len;i++) {
            this.weak[i] = weak[i];
            this.weak[i+len] = weak[i] + n;
        }

        visit = new boolean[dist.length];
        // 친구들 순열 만들어서 외벽 점검 할 수 있는지 확인
        makeFriends(0);
        answer = ans == 10 ? -1 : ans;
        return answer;
    }
    void makeFriends(int idx) {
        // 끝까지 다 확인한 상태면 check() 호출
        int len = dist.length;
        if(idx == len) {
            check();
        }
        for(int i=0; i<len; i++) {
            if(visit[i]) continue;
            visit[i] = true;
            friends.add(dist[i]);
            makeFriends(idx+1);
            visit[i] = false;
            friends.remove(friends.size()-1);
        }
    }
    // 외벽을 점검할 수 있는지 확인하는 메소드.
    // 외벽을 점검할 수 있고, 인원 수가 최소라면 정답 갱신
    void check() {
        int fn = friends.size();
        // 외벽의 모든 점을 시작점으로 해서 확인해야한다.
        for(int i=0;i<wn;i++) {
            // weakIdx는 시작점으로 i로 설정. i ~ i + 취약지점의 갯수 까지 확인
            int friendsIdx = 0, weakIdx = i;
            // 반복문이 종료되는 조건
            // 1. 모든 친구 확인 || 2. 모든 취약지점 확인 -> 2번으로 종료된 경우 현재 선택된 친구들로 외벽 점검 가능 -> 정답 갱신
            while(friendsIdx < fn && weakIdx < i+wn) {
                // 이번 친구로 갈 수 있는 최대한 먼 취약 지점 까지 이동
                int nextW = weakIdx + 1;
                while (nextW < i+wn && weak[weakIdx] + friends.get(friendsIdx) >= weak[nextW]) {
                    nextW++;
                }
                // 이제 다음 취약 지점부터 다음 친구로 확인
                // friendsIdx 가 0부터 시작하므로 이렇게 하면 명 수 나옴
                weakIdx = nextW;
                friendsIdx++;
            }
            // 모든 취약 지점을 살펴 봤다면 정답 갱신
            if(weakIdx == i+wn)
                ans = ans > friendsIdx ? friendsIdx : ans;
        }
    }
}
