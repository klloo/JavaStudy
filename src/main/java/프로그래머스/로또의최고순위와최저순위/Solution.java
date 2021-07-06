package main.java.프로그래머스.로또의최고순위와최저순위;

import java.util.*;
import java.util.regex.Pattern;

public class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {};

        answer = new int[2];
        answer[0] = 7;
        answer[1] = 7;

        for(int lotto:lottos) {
            if(lotto==0)
                answer[0]--;
            else {
                for (int win_num : win_nums) {
                    if (win_num == lotto) {
                        answer[0]--;
                        answer[1]--;
                    }
                }
            }
        }

        answer[0] = answer[0]==7?6:answer[0];
        answer[1] = answer[1]==7?6:answer[1];

        return answer;
    }
}
