package 프로그래머스.블록이동하기;


import main.java.프로그래머스.블록이동하기.Solution;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        int[][] board = {{0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 1, 1, 1, 0}, {0, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 1, 1}, {0, 0, 1, 0, 0, 0, 0}};
        int res = solution.solution(board);
        System.out.println(res);
    }
}