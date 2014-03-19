/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        helper(root,array);
        return array;
    }
    
    public void helper(TreeNode p, ArrayList<Integer> array){
        if(p==null) return;
        array.add(p.val);
        helper(p.left,array);
        helper(p.right,array);
    }
}
