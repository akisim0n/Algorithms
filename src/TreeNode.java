import java.util.HashMap;
import java.util.Map;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder.length == 0) return null;
        Map<Integer, Integer> inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildNode(0,0, preorder.length - 1, preorder, inorderMap);
    }

    public TreeNode buildNode(int preIndex, int left, int right, int[] preorder, Map<Integer, Integer> inorderMap) {

        TreeNode root = new TreeNode(preorder[preIndex]);

        int inorderRootIndex = inorderMap.get(root.val);

        if (inorderRootIndex  == left || inorderRootIndex == right) {
            return root;
        }

        root.left = buildNode(preIndex + 1, left,right - inorderRootIndex, preorder, inorderMap);
        root.right = buildNode(preIndex + (preIndex) + 1,left + (inorderRootIndex - preIndex - 1), right, preorder, inorderMap);

        return root;
    }

}
