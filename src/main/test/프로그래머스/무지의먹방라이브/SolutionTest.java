package main.test.프로그래머스.무지의먹방라이브;

import main.java.프로그래머스.무지의먹방라이브.Solution;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        int[] food_times = {3,1,2,4};
        long k = 5;
        int ans = solution.solution(food_times,k);
        System.out.println(ans);
    }

}