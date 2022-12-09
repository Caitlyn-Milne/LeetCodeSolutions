/*
Intuition
We are trying to maximize the difference between ancestor and a descendant. 
If we traverse the tree, keeping track of the maxium seen so far, 
then we find the greatest absolute difference if the descendant is lower than it. 
Ok but what if the best answer is actually the descendant being higher,
simple, we can also keep track of the lowest value as well.

Approach
Breath first search, keep track of the lowest and highest value.

Complexity
Time O(n)
Space O(n)
*/

fun maxAncestorDiff(root: TreeNode?): Int {
    if(root == null) return 0
    val queue = ArrayDeque<Triple<TreeNode, Int, Int>>()
    queue.addLast(Triple(root, root.`val`, root.`val`))
    var result = Int.MIN_VALUE
    while (queue.size > 0) {
        var (node, min, max) = queue.removeFirst()
        val value = node.`val`
        val absMin = Math.abs(min - value)
        val absMax = Math.abs(max - value)
        result = Math.max(absMin,result)
        result = Math.max(absMax,result)
        min = Math.min(min, value)
        max = Math.max(max, value)
        node.left?.let { left ->  
            queue.addLast(Triple(left, min, max))
        }
        node.right?.let { right ->  
            queue.addLast(Triple(right, min, max))
        }
    }
    return result
}
