class MedianFinder {

    // 默认小顶堆
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    // 大顶堆
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((i1, i2) -> i2-i1);


    /** initialize your data structure here. */
    public MedianFinder() {
        
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
            return;
        }
        if (num >= maxHeap.peek()) {
            minHeap.add(num);
            if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.add(minHeap.poll());
            }
        } else {
            maxHeap.add(num);
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            }
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else if (maxHeap.size() < minHeap.size()) {
            return minHeap.peek();
        }
        return ((double)maxHeap.peek() + (double)minHeap.peek())/2d;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */