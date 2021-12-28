package main.java.프로그래머스.다단계칫솔판매;

import java.util.*;

public class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};
        Map<String,String> parentMap = new HashMap<>();
        Map<String,Integer> totalProfit = new HashMap<>();

        // 키:자식 값:부모 인 맵과 키:사람 값:그 사람의 이익(초기값 0)인 맵 두개 생성
        int len = enroll.length;
        for(int i=0; i<len; i++) {
            parentMap.put(enroll[i], referral[i]);
            totalProfit.put(enroll[i],0);
        }

        // 각 판매자의 이익 계산
        len = seller.length;
        for(int i=0; i<len; i++) {
            // 현재 판매자와 판매자의 이익(판매량 * 100) 계산
            int profit = amount[i]*100;
            String sell = seller[i];
            // 부모에게 이익 전달
            while(true) {
                // 만약 더이상 이익이 없거나 루트에 도달 했다면 반복문 종료
                if(profit == 0 || sell.equals("-"))
                    break;
                // 현재 판매자의 이익은 기존 가지고 있던 이익 + 새로 얻게된 이익의 90%
                int sellerTotalProfit = totalProfit.get(sell) + (profit-(profit/10));
                totalProfit.put(sell, sellerTotalProfit);
                // 이익은 10%만 남기고 부모에게 이익 전달 (이익이 저장될 판매자를 부모로 설정)
                profit /= 10;
                sell = parentMap.get(sell);
            }
        }

        // enroll에 등장하는 순서대로 총 이익 꺼내서 정답 배열에 넣어주기
        len = enroll.length;
        answer = new int[len];
        for(int i=0; i<len; i++) {
            answer[i] = totalProfit.get(enroll[i]);
        }
        return answer;
    }
}
