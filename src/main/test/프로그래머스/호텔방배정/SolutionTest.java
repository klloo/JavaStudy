package main.test.프로그래머스.호텔방배정;

import main.java.프로그래머스.호텔방배정.Solution;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        long k = 10;
        long[] room_number = {1,3,4,1,3,1};
        long[] ans = solution.solution(k,room_number);
        for(long n:ans){
            System.out.print(n+" ");
        }

    }
}