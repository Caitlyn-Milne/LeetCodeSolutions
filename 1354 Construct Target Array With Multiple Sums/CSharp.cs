/*
PROBLEM
******************************

You are given an array target of n integers. From a starting array arr consisting of n 1's, you may perform the following procedure :

let x be the sum of all elements currently in your array.
choose index i, such that 0 <= i < n and set the value of arr at index i to x.
You may repeat this procedure as many times as needed.
Return true if it is possible to construct the target array from arr, otherwise, return false.

Constraints:

n == target.length
1 <= n <= 5 * 104
1 <= target[i] <= 109


Observations and problem solving
***********************************

There are some intresting observations to be made about this problem. 
    - Firstly because the sum can only increase, the highest number has to be set last (in terms of the direction the problem is explained)
    - Secondly when setting i, the difference between its old value and its new value is the sum of the arrays other elements

To approach this problem I tried imagining the possibility space, when I thought what if I worked backwards from the target to the array 0f 1s,

using the previous observations I discovered there is only one possible move moving backwards, and with refinement I ended up with my solution.


Solution explained
**********************************

if the array is size 1 you can not use the other elements in the array to change it therefore if its already one, its possible, else, not possible

We are going to ask what is the largest value alot, so to make this operation faster, I create a priorityQueue. 
PriorityQueues can be dequeue to get the element with the smallest priority, this is known as a min heap. We however want a max heap, 
so we set the prioty to the negation of the value.

we are going to keep track of the sum of the elements in the max heap as we go along

now we have our data structures set up, we can get the largest value in the heap, and if its one, then the array must all be ones. (target[i] minium value is one)

we now work out the sum of all other elements by simple subtracting the largestValue we just found from the total sum

if the largest value is smaller then the sum of the others, it couldnt possibly be valid because that means that value would have to be less than one during for that operation,
and the elements start at one and can only increase

Mow we can modify the previously largestValue by the sum of the others. As long as its the largest value we are going decrease it by the sum, so we can use modola to do these in one operation. 

if the largest value is now zero then you cant make it from starting with one and adding the sum, so its not possible
of course if the sum of the others is one, then every number can be made from it, and so this is only true if thats not the case

finally we can enqueue up the new value and correct the sum before, we do the cycle again, eventually returning true or false.

Time Complexity
********************
Time Complexity O(n log n)
Space Complexity O(n)
*/



public class Solution
{
    public bool IsPossible(int[] target)
    {
        if (target.Length == 1) return target[0] == 1;

        var heap = new PriorityQueue<long, long>();
        long sum = 0;
        foreach (var item in target)
        {
            heap.Enqueue((long)item, -(long)item);
            sum += item;
        }

        while (heap.Peek() > 1)
        {
            var largestValue = heap.Dequeue();
            sum -= largestValue;

            if (largestValue <= sum)
                return false;

            largestValue %= sum;

            if (sum != 1 && largestValue == 0) return false;

            sum += largestValue;

            heap.Enqueue(largestValue, -largestValue);
        }
        return true;
    }
}