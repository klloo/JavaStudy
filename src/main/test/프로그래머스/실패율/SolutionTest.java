package main.test.프로그래머스.실패율;

import main.java.프로그래머스.실패율.Solution;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        int n = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        int ans[] = solution.solution(n,stages);
        for(int a:ans)
            System.out.println(a);
    }

}