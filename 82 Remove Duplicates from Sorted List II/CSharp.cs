/*
 For any given node during the linked list, it is unique if its neighbouring nodes 
hold different values. This is due to the fact the input list is sorted.

To apply this to a linked list:
********************************

we first need the previous node and next node for any given node (head), 
this can be achieved through the next pointer, and by having the previous node passed through 
as a parameter during recursion.

Given that the result of the recurse will be the last unique node, 
we can set our next pointer to this.

We can now work out what to return, 
which should be the last node that is unique.

If the current head is the last node that is unique (different then its neighbours), 
we should return it, else we should return the last unique node, which is the result of the recurse, being stored in the next pointer.

The base case of this recursion is meeting the end of the linked list, 
aka a pointer to null.

The Complexity:
*****************************

The time complexity is linear time O(N) as it recuses through the linked list once.
The space complexity is linear time O(N) as it creates a call once for each item of the list
that must be stored on the callstack.

Comments:
*******************************

This approach returns a head of a linked list that only points to unique nodes,
however non-unique nodes still may point to nodes inside the returned linked list.
If this becomes a problem it might be worth derefencing any non unique nodes and its next pointers.
C# has a garbage collector so this is not likely to be that big of a deal, 
but it might cause memory leaks in other languages.

 */
public class Solution
{
    public ListNode DeleteDuplicates(ListNode head, ListNode prevNode = null)
    {
        if (head == null) return null;

        ListNode nextNode = head.next;
        head.next = DeleteDuplicates(head.next, head);

        if ((nextNode == null || nextNode.val != head.val) &&
            (prevNode == null || prevNode.val != head.val))
        {
            return head;
        }

        return head.next;
    }
}