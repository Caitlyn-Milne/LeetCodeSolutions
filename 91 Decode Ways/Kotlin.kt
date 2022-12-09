/* 
Intuition
At any given index, there is two possible options. 
1 - this number represents the character 1-9. 
2 this number presents the first digit of a two digit number with the value 10-26. 
(We dont consider the option for being the second digit, 
because that would be handled by the previous item's option 2)

Approach
If you draw the decision tree using the above intuition as the options at each part 
of the tree you'll find out that the answer is the number of leaves along the decision tree.
I optimized this first with memoization, and then wrote a tabulation approach. 
*/

//time O(n)
//space O(n)
fun numDecodingsMemo(s: String): Int {
    val memo = IntArray(s.length) { -1 }
    fun dp(i : Int) : Int {
        if (i >= s.length) return 1
        if(s[i] == '0') return 0
        if (i == s.length - 1) return 1
        if (memo[i] != -1) {
            return memo[i]
        }
        val left = dp(i + 1)
        var right = 0
        if ("${s[i]}${s[i + 1]}".toInt() <= 26){
            right = dp(i + 2)
        }
        memo[i] = left + right
        return memo[i]
    }
    return dp(0)
}

//time O(n)
//space O(1)
fun numDecodingsTabulation(s: String): Int {
    if (s.length == 0) return 0
    var prev = if(s[s.length -1] == '0') 0 else 1
    var prevPrev = 1
    for (i in s.length - 2 downTo 0) {
        var score = 0
        if(s[i] != '0') {
            if("${s[i]}${s[i + 1]}".toInt() <= 26)
                score += prevPrev
            score += prev
        }
        prevPrev = prev
        prev = score
    }
    return prev
}
