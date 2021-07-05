class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;        
        this.postorder = postorder;
        for (int i = 0; i < inorder.length; i++) {
            this.indexMap.put(inorder[i], i);
        }
        return buildTree(0, inorder.length - 1, inorder.length - 1);
    }

    private TreeNode buildTree(int leftBoundaryOfInOrder, int rightBoundaryOfInOrder, int indexOfPostOrder) {
        // 递归终止条件
        if (leftBoundaryOfInOrder > rightBoundaryOfInOrder) {
            return null;
        }
        int value = postorder[indexOfPostOrder];
        TreeNode treeNode = new TreeNode(value);
        Integer indexOfInOrder = indexMap.get(value);
        // 从后续遍历拿到尾端点, 递归右子树
        treeNode.right = buildTree(indexOfInOrder + 1, rightBoundaryOfInOrder, indexOfPostOrder - 1);
        // 拿到右子树长度
        int rightSubTreeLength = rightBoundaryOfInOrder - (indexOfInOrder + 1) + 1;
        // 从后续遍历拿到尾端点, 递归左子树
        treeNode.left = buildTree(leftBoundaryOfInOrder, indexOfInOrder - 1, indexOfPostOrder - rightSubTreeLength - 1);
        return treeNode;
    }

    Map<Integer, Integer> indexMap = new HashMap<>();
    int[] inorder;
    int[] postorder;
}