package main.test.프로그래머스.합승택시요금;

import main.java.프로그래머스.합승택시요금.Solution;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        int n = 7;
        int s = 3;
        int a = 4;
        int b = 1;
        int[][] fares = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};
        int result = solution.solution(n,s,a,b,fares);
        System.out.println(result);
    }
}