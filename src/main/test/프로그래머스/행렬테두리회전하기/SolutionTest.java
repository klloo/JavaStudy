package main.test.프로그래머스.행렬테두리회전하기;

import main.java.프로그래머스.행렬테두리회전하기.Solution;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        int rows = 6;
        int colunms = 6;
        int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
        int[] ans = solution.solution(rows,colunms,queries);
        for(int a:ans)
            System.out.print(a+" ");
    }

}