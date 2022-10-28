using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace LeetCodeSolutions._1696_Jump_Game_VI
{
    public class Solution
    {
        public int MaxResult1(int[] nums, int k)
        {
            LinkedList<int> linklist = new LinkedList<int>();
            linklist.AddLast(nums[^1]);
            for (int i = nums.Length - 2; i >= 0; i--)
            {
                var max = int.MinValue;
                foreach (var x in linklist)
                {                    
                    if (x > max) max = x;                    
                }
                linklist.AddLast(nums[i] + max);
                if (linklist.Count > k) linklist.RemoveFirst();
                
            }
            return linklist?.Last?.Value ?? 0;
        }

        public int MaxResult(int[] nums, int k)
        {
            PriorityQueue<int,int> heap = new PriorityQueue<int,int>();
            var max = 0;
            for (int i = nums.Length - 1; i >= 0; i--)
            {
                var prev = 0;
                while (heap.TryPeek(out var index, out prev)
                    && index < i - k)
                {
                    heap.Dequeue();
                }

                max = nums[i] + prev;
                heap.Enqueue(i, max);
            }
            return max;
        }
    }
}
