package main.test.프로그래머스.로또의최고순위와최저순위;

import main.java.프로그래머스.로또의최고순위와최저순위.Solution;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        int[] lottos = {31, 45, 1, 10, 19, 0};
        int[] win_nums = {31, 10, 45, 1, 6, 19};
        int[] ans = solution.solution(lottos,win_nums);
        for(int a:ans)
            System.out.print(a+" ");
    }
}