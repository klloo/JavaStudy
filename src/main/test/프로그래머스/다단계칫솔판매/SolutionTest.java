package main.test.프로그래머스.다단계칫솔판매;

import main.java.프로그래머스.다단계칫솔판매.Solution;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void solutionTest() {
        Solution solution = new Solution();
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"sam", "emily", "jaimie", "edward"};
        int[] amount = {2, 3, 5, 4};
        int[] ans = solution.solution(enroll,referral,seller,amount);
        for(int a:ans)
            System.out.print(a+" ");
    }

}