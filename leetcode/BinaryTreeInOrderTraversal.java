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
