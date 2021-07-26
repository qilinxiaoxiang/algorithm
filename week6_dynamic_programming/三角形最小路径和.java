class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // 增加一个保护列
        int row = triangle.size();
        int length = triangle.get(row-1).size() + 1;
        int[] dp = new int[length];
        // 初始化
        for (int i = 0; i < length; i++) {
            dp[i] = 100000000;
        }
        // 0是保护列
        dp[1] = triangle.get(0).get(0);
        // 填格子
        for (int rowIndex = 1; rowIndex < triangle.size(); rowIndex++) {
            List<Integer> list = triangle.get(rowIndex);
            for (int i = list.size(); i > 0; i--) {
                dp[i] = Math.min(dp[i] + list.get(i-1), dp[i-1] + list.get(i-1));
            }   
        } 
        // 找最值
        int result = 100000000;
        for (int i = 0; i < length; i++) {
            result = Math.min(result, dp[i]);
        }
        return result;
    }
}