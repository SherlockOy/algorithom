/*
Given two sorted integer arrays A and B, merge B into A as one sorted array.

Note:
You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.


*/
public class Solution {
    public void merge(int A[], int m, int B[], int n) {
        int[] C = new int[m];
        for(int i = 0; i<m;i++)
            C[i] = A[i];
        int i,j,k;
        i=0;
        j=0;
        k=0;
        for(k=i;i<m&&j<n;k++){
            if(C[i]<=B[j])
                A[k] = C[i++];
            else
                A[k] = B[j++];
        }
        while(i<m)
            A[k++] = C[i++];
        while(j<n)
            A[k++] = B[j++];
    }
}
