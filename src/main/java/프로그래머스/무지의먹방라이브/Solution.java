package main.java.프로그래머스.무지의먹방라이브;

import java.util.*;

class Food {
    private int times;
    private int num;
    public Food(int times,int num) {
        this.times = times;
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public int getTimes() {
        return times;
    }

}
public class Solution {
    public int solution(int[] food_times, long k) {
        int answer = 0;

        List<Food> foodList = new ArrayList<Food>();

        //음식 번호와 시간으로 푸드리스트 만들기
        int idx = 1;
        for(int foodTime:food_times)
            foodList.add(new Food(foodTime,idx++));

        //시간의 오름차순으로 정렬
        foodList.sort((Food a, Food b)->{
            return a.getTimes() - b.getTimes();
        });

        int len = foodList.size();
        long ate;
        int n, i, ansIdx = 0,prevTime = 0;

        //foodList 순차 탐색
        for(i=0;i<len;i++) {
            n = len - i;
            Food food = foodList.get(i);

            //이번 음식(제일 적은 음식)을 다 먹으면서, 한 로테이션에서 먹을 수 있는 양 계산 = (현재 음식의 시간 - 이전 음식의 시간) * 앞으로 남은 음식
            ate = ((long)food.getTimes() - prevTime) * n;

            //만약 이번 음식을 다 먹으면서 한바퀴 돌 수 있으면 k에서 먹은 양(시간)만큼 빼고 prevTime 갱신
            if(ate <= k) {
                k -= ate;
                prevTime = food.getTimes();
            }
            else {
                //한바퀴 못돌면 남은 음식을 인덱스 순으로 정렬해서 정답이 되는 음식의 인덱스 계산
                //제일 적은 음식을 다 먹지 못한다면 남은 음식들은 k/n바퀴를 돌고 k%n번째 음식까지 먹을 수 있음
                foodList.subList(i,len).sort((Food a, Food b) ->{
                    return a.getNum() - b.getNum();
                });
                //정답 음식의 인덱스는 남은 음식들 중 k%n 번째 음식!
                ansIdx = i + (int)(k%n);
                break;
            }
        }
        answer = (i < len) ? foodList.get(ansIdx).getNum() : -1;
        return answer;
    }
}
