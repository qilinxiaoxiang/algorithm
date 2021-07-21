class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = Integer.MAX_VALUE - left;
        while (left < right) {
            int mid = (left + right) / 2;
            if (eatTimeCost(mid, piles) > h) left = mid + 1;
            else right = mid;
        }
        return right;
    }

    private int eatTimeCost(int mid, int[] piles) {
        return Arrays.stream(piles).map(p -> (p-1)/mid + 1).sum();
    }
}