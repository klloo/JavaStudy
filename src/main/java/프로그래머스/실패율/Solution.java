package main.java.프로그래머스.실패율;

import java.util.*;

class FailInfo implements Comparable<FailInfo> {
    private int stage;
    private float failRate;

    public int getStage() {
        return stage;
    }

    public FailInfo(int stage, float failRate) {
        this.stage = stage;
        this.failRate = failRate;
    }

    //실패율은 내림차순, 스테이지는 오름차
    @Override
    public int compareTo(FailInfo o) {
        if(o.failRate > this.failRate)
            return 1;
        else if(o.failRate == this.failRate)
            if(o.stage < this.stage)
                return 1;
        return -1;
    }

}
public class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = {};
        //인덱스 스테이지에서 실패한 사용자
        int[] failPlayer = new int[N+2];
        //인덱스 스테이지에서 도달한 사용자
        int[] reachPlayer = new int[N+2];

        for(int stage:stages) {
            failPlayer[stage]++;
            for(int i=1;i<=stage;i++)
                reachPlayer[i]++;
        }

        ArrayList<FailInfo> failInfoList = new ArrayList<FailInfo>();

        //실패율 계산. 아무도 스테이지에 도달하지 못했으면 0으로 나누기 -> NaN 나옴 따라서 분모가 0이면 0을 직접 넣어줌
        for(int i=1;i<=N;i++) {
            float res = reachPlayer[i]==0? (float) 0 :(float)failPlayer[i]/reachPlayer[i];
            FailInfo newFail = new FailInfo(i,res);
            failInfoList.add(newFail);
        }

        //정렬 후 정답 저장
        Collections.sort(failInfoList);

        answer = new int[N];
        int idx = 0;
        for (FailInfo f : failInfoList) {
            answer[idx++] = f.getStage();
        }

        return answer;
    }
}
