package main.test.프로그래머스.징검다리건너기;

import main.java.프로그래머스.징검다리건너기.Solution;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        int ans = solution.solution(stones,k);
        System.out.println(ans);
    }

}