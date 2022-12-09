//solution 1
//create recursively create subsets for the array excluding the first element,
//for each subset, create a new subset with first number added
//time O(n x 2^n)
//space O(n x 2^n) if output is included or O(n) for operations
fun subsets1(nums: IntArray): List<List<Int>> {
    var sets = mutableListOf<List<Int>>(listOf<Int>())
    
    fun impl(left : Int,nums: IntArray){
        if(nums.size - left == 0) return   
        
        impl(left + 1, nums)
        for(i in sets.size - 1 downTo 0){
            sets.add(listOf(nums[left]) + sets[i])
        }
    }    
    
    impl(0, nums)
    return sets
}

//solution 2
//create an empty subset
//foreach num, create a new subsets for each subset with that number included
//time O(n x 2^n)
//space O(n x 2^n) if output is included or O(n) for operations
fun subsets2(nums: IntArray): List<List<Int>> {
    var sets = mutableListOf(listOf<Int>())
    nums.forEach{ num -> 
        for(i in sets.size - 1 downTo 0){
           sets.add(listOf(num) + sets[i]) 
        }      
    }
    return sets
}

//solution 3 - backtracking
//recursively explore the decision tree (only looking at indexes bigger to avoid duplicates)
//time O(n x 2^n)
//space O(n x 2^n) if output is included or O(n) for operations
fun subsets3(nums: IntArray): List<List<Int>> { 
    val sets = mutableListOf<List<Int>>()
    fun backtracking(left : Int ,nums: IntArray, currentSet : List<Int>) {
        sets.add(currentSet)
        for(i in left until nums.size){
            backtracking(i + 1, nums, currentSet + listOf<Int>(nums[i]))
        }
    }
    backtracking(0 ,nums, listOf<Int>())
    return sets
}
