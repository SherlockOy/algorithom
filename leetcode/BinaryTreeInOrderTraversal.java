/**

Given a binary tree, return the inorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [1,3,2].

Note: Recursive solution is trivial, could you do it iteratively?



 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        if(root==null) 
            return array;
        helper(array, root);
        return array;
        
    }
    public void helper(ArrayList<Integer> array, TreeNode n){
        
        if(n==null) return;
        helper(array, n.left);
        array.add(n.val);
        helper(array, n.right);
        
    }
}
