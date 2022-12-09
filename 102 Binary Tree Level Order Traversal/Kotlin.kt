//time O(n)
//space O(n)
fun levelOrder(root: TreeNode?): List<List<Int>> {
    var result = mutableListOf<MutableList<Int>>() 
    if (root == null) return result as List<List<Int>>
    val queue = ArrayDeque<Pair<TreeNode, Int>>()
    queue.addLast(Pair(root,0))
    while (queue.size > 0) {
        val (node, level) = queue.removeFirst()
        if (level >= result.size) {
            result.add(mutableListOf<Int>())
        }
        result[level].add(node.`val`)
        node.left?.let { left -> 
            queue.addLast(Pair(left, level + 1)) 
        }
        node.right?.let { right -> 
            queue.addLast(Pair(right, level + 1)) 
        }
    }
    return result as List<List<Int>>
}
