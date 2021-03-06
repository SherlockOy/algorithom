/**

Given a binary tree, return the postorder traversal of its nodes' values.

For example:
Given binary tree {1,#,2,3},
   1
    \
     2
    /
   3
return [3,2,1].

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
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        helper(root,array);
        return array;
    }
    public void helper(TreeNode p, ArrayList<Integer> array){
        if(p==null)
            return ;
        helper(p.left,array);
        helper(p.right,array);
        array.add(p.val);
    }
}
