class Twitter {

    Map<Integer, Set<Integer>> follow;
    Map<Integer, ListNode> twitter;
    int primaryKey = 1;
    /** Initialize your data structure here. */
    public Twitter() {
        // 表结构设计
        // 用户关注了谁
        follow = new HashMap<>();
        // 用户发了哪些微博
        twitter = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (twitter.containsKey(userId)) {
            ListNode lastTwitter = twitter.get(userId);
            ListNode newTwitter = new ListNode(primaryKey++, tweetId);
            newTwitter.next = lastTwitter;
            twitter.put(userId, newTwitter);
        } else {
            twitter.put(userId, new ListNode(primaryKey++, tweetId));
        }
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        ArrayList<Integer> answer = new ArrayList<>();
        // 取出用户关注的人
        Set<Integer> followeeIdSet = new HashSet<>();
        followeeIdSet.add(userId);
        Set<Integer> set = follow.get(userId);
        if (set != null) {
            followeeIdSet.addAll(set);
        }
        // 合并k个降序链表
        // 大顶堆
        PriorityQueue<Node> maxHeap = new PriorityQueue<>((n1, n2) -> n2.key - n1.key);
        for (Integer followeeId : followeeIdSet) {
            ListNode listNode = twitter.get(followeeId);
            if (listNode == null) {
                continue;
            }
            maxHeap.add(new Node(listNode.id, listNode));
        }
        while (!maxHeap.isEmpty() && answer.size() < 10) {
            Node node = maxHeap.poll();
            answer.add(node.listNode.val);
            ListNode nextNode = node.listNode.next;
            if (nextNode != null) {
                maxHeap.add(new Node(nextNode.id, nextNode));
            }
        }
        return answer;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        follow.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        follow.computeIfPresent(followerId, (k,v) -> {
            v.remove(followeeId);
            return v;
        });
    }
}

class Node {
    int key;
    ListNode listNode;
    Node(int key, ListNode listNode) {
        this.key = key;
        this.listNode = listNode;
    }
}

class ListNode {
    public int id;
    public int val;
    public ListNode next = null;
    public ListNode(int id, int val) {
        this.id = id;
        this.val = val;
    }
}
/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */