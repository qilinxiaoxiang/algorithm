class Solution {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] methodCounts = new int[n + 1];
        for(int i = 0; i < n + 1; i++) {
            methodCounts[i] = 0;
        }
        methodCounts[1] = 1;
        methodCounts[2] = 2;
        for(int i = 3; i <= n; i++) {
            methodCounts[i] = methodCounts[i-1] + methodCounts[i-2];
        }
        return methodCounts[n];
    }
}