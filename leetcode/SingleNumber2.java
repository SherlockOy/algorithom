/*Given an array of integers, every element appears three times except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Important:
I failed to solve this problem under the constraint of not using extra memory, here is a solution from 1337c0d3r at http://oj.leetcode.com/discuss/857/constant-space-solution
thank you for this neat and great solution.
*/

public class Solution {
    public int singleNumber(int[] A) {
    int ones = 0, twos = 0, threes = 0;
        for (int i = 0; i < A.length; i++) {
            twos |= ones & A[i];
            ones ^= A[i];
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;
        }
    return ones;
    }
}
