package 프로그래머스.미로탈출;

import main.java.프로그래머스.미로탈출.Solution;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        int n = 4;
        int start = 1;
        int end = 4;
        int[][] roads = { {1, 2, 1}, {3, 2, 1}, {2, 4, 1} };
        int[] traps = { 2,3 };
        int result = solution.solution(n, start, end, roads, traps);
        System.out.println(result);
    }
}