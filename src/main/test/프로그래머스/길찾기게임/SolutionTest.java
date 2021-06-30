package main.test.프로그래머스.길찾기게임;

import main.java.프로그래머스.길찾기게임.Solution;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {
    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        int[][] nodeinfo = {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}};
        int[][] answer = solution.solution(nodeinfo);
        for(int[] ans :answer){
            for(int a : ans)
                System.out.print(a+",");
            System.out.println();
        }
    }
}