//solution 1 brute force:
//The obvious way to do this would be map each element of the array to its square and then sort it
// time O(nLogn) for sorting
// space O(n)
fun sortedSquaresBruteForce(nums: IntArray): IntArray {
    val result = nums.map {num -> num * num }.toIntArray()
    result.sort()
    return result
}

//solution 2 merge:
//A optimal solution in terms of complexity, but is maybe alittle complicated is to
//remove all elements below 0, find there absolute values and then merge them back in, 
//before squaring each element
//time O(n)
//space O(n)
fun sortedSquaresMerge(n: IntArray): IntArray {
    var nums = n
    var split = 0
    while(split < nums.size){
        if(nums[split] > 0) break
        split++
    }
    for(i in 0 until split){
        nums[i] = Math.abs(nums[i])
    }
    nums = merge(nums, split)
    return nums.map {num -> num * num }.toIntArray()
}

fun merge(nums:IntArray, pivot: Int) : IntArray{
    var leftA = 0
    var rightA = pivot - 1
    var leftB = pivot
    var rightB = nums.size -1
    var merged = IntArray(nums.size)
    var i = 0
    
    while(leftA <= rightA && leftB <= rightB){
        if(nums[rightA] < nums[leftB]){
            merged[i] = nums[rightA]
            rightA--
        }
        else {
            merged[i] = nums[leftB]
            leftB++
        }
        i++
    }
    
    while(leftA <= rightA){
        merged[i] = nums[rightA]
        rightA--
        i++
    }
    
    while(leftB <= rightB){
        merged[i] = nums[leftB]
        leftB++
        i++
    }
    
    return merged
}

//solution 3 two pointers:
//The previous solution can actually be simplified, 
//what we are actually doing before is comparing the left and the right sside of the array
//we can do that directly with two pointers. 
//time O(n)
//space O(n)
fun sortedSquaresTwoPointers(nums: IntArray): IntArray {
    var left = 0
    var right = nums.size -1
    val result = IntArray(nums.size)
    var i = nums.size -1
    while(left <= right){
        if(Math.abs(nums[left]) >= Math.abs(nums[right])){
            result[i--] = nums[left] * nums[left]
            left++
        }
        else {
            result[i--] = nums[right] * nums[right]
            right--
        }        
    }
    return result
}