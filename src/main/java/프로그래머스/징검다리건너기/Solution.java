package main.java.프로그래머스.징검다리건너기;


import java.util.Arrays;

public class Solution {
    public boolean isPossible(int[] stones, int k, int n){
        int cnt = 0;
        for(int stone:stones) {
            if((stone - n) < 0) {
                cnt++;
                if(cnt>=k)
                    return false;
            }
            else
                cnt = 0;
        }
        return true;
    }
    public int solution(int[] stones, int k) {
        int answer = 0;
        int max = Arrays.stream(stones).max().getAsInt();
        int min = Arrays.stream(stones).min().getAsInt();
        int mid = 0;
        while(min <= max) {
            mid = (min+max)/2;
            if(isPossible(stones,k,mid)) {
                min = mid + 1;
                answer = answer > mid ? answer : mid;
            }
            else
                max = mid-1;
        }
        return answer;
    }
}
