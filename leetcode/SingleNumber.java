/*
Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/


public class Solution {
    public int singleNumber(int[] A) {
        int i;
        for(i = 0; i< A.length-1; i++){
            A[i+1] = A[i+1]^A[i];
        }
        return A[i];
    }
}
