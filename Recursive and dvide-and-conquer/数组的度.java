class Solution {
    public int findShortestSubArray(int[] nums) {
        int maxCount = 0;
        int minLength = 0;
        Map<Integer, Record> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                Record record = map.get(num);
                record.count = record.count + 1;
                if (record.count > maxCount) {
                    maxCount = record.count;
                    minLength = i - record.firstIndex + 1;
                } else if (record.count == maxCount) {
                    int length = i - record.firstIndex + 1;
                    if (length < minLength ) {
                        minLength = length;
                    }
                }
            } else {
                map.put(num, new Record(i));
                if (maxCount < 1) {
                    maxCount = 1;
                    minLength = 1;
                }
            }            
        }
        return minLength;
    }
}

class Record {
    final int firstIndex;
    int count;

    public Record(int firstIndex) {
        this.firstIndex = firstIndex;
        this.count = 1;
    }
}