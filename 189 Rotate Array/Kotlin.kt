//solution 1
//clone array, for each value put it in the orignal array at pos + k % arrayLength

//time complexity O(n)
//space O(n)
fun rotateClone(nums: IntArray, k: Int): Unit {
    var copy = nums.clone()
    copy.forEachIndexed{ index,num ->
        var newIndex = (index + k) % nums.size
        nums[newIndex] = num
    }
}

//solution 2
//edge case element of size 1 or 0, return self
//for each rotation
//hold = 0th position
//i = 1
//swap hold and i
//at the end put hold into the start of the array 

//time complexity O(n * k)
//space complexity O(1)
fun rotateSwaps(nums: IntArray, _k: Int): Unit {
    var k = _k % nums.size
    if(nums.size == 0 || nums.size == 1 || k == 0)
        return
    
    for(ii in 0 until k){
        var i = 0
        var hold = nums[0]
        do{
            i++
            i %= nums.size
        
            var temp = hold
            hold = nums[i]
            nums[i] = temp
        } while(i != 0)
    }
}

//solution 3, we can reverse the array, reverse which where the last k elements, now they are the first k elements, and reverse the other elements
//time O(n)
//space O(1)
fun rotateReverse(nums: IntArray, k: Int): Unit {
    var _k = k % nums.size
    reverse(nums, 0, nums.size - 1)
    reverse(nums, 0, _k - 1)
    reverse(nums, _k, nums.size - 1)
}
fun reverse(nums: IntArray, left: Int, right: Int){
    var l = left
    var r = right
    while(l < r){
        var temp = nums[l]
        nums[l++] = nums[r]
        nums[r--] = temp
    }
}