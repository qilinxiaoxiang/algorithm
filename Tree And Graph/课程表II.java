import java.util.Optional;
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> result = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer, List<Integer>> prerequisiteMap = new HashMap<>();
        int[] prerequisiteCount = new int[numCourses];
        // 1. 遍历原始二位数组, 记录每门课的直接后续课程, 以及每门课需要的前置课程数
        // 2. 遍历前置课程数为0的课程, 入队
        // 3. 每出队一个课程, 就遍历它直接后续课程, 将他们的前置课程数数都减一, 如果减到0, 则入队
        for (int[] prerequisite : prerequisites) {
            prerequisiteMap.computeIfAbsent(prerequisite[1], k -> new ArrayList<>()).add(prerequisite[0]);
            prerequisiteCount[prerequisite[0]]++;
        }
        for (int i = 0; i < prerequisiteCount.length; i++) {
            if (prerequisiteCount[i] == 0) {
                deque.offer(i);
            }
        }
        while (!deque.isEmpty()) {
            Integer p = deque.poll();
            result.add(p);
            if (prerequisiteMap.containsKey(p)) {
                prerequisiteMap.get(p).forEach(i-> {
                    prerequisiteCount[i]--;
                    if (prerequisiteCount[i] == 0) {
                        deque.offer(i);
                    }
                });
            }
        }
        if (result.size() == numCourses) {
            int[] resultArray = new int[result.size()];
            for (int i = 0; i < result.size(); i++) {
                resultArray[i] = result.get(i);
            }
            return resultArray;
        }
        return new int[]{};
    }
}