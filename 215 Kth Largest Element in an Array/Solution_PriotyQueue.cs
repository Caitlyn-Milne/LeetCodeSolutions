/*
Prioty Queue

This solutions uses c# in built prioty queue to act as a max heap.
by default priority queue acts like a min heap,
so we use the negation of the number as the priority when building the heap.
you could also solve this issue by creating a comparitor,

the program flow goes like this, first a 'max heap' is created, where the numbers are inserted.
next we dequeue data out of the heap k-1 times,
finally we return the next dequeue.

the time complexity is O(n log n)
the space complexity is O(n)
*/

public class Solution {
    public int FindKthLargest(int[] nums, int k) {
        PriorityQueue<int,int> heap = new();
        heap.EnqueueRange(nums.Select(n => (n,-n)));
        for(int i = k; i > 1; i--){
            heap.Dequeue();
        }
        return heap.Dequeue();
    }
}