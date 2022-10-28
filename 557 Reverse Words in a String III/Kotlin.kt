class Solution {
    fun reverseWords(s: String): String {
        var chars = s.toCharArray()
        var left = 0
        for(index in 0 until chars.size){
            if(chars[index] != ' ') continue
            reverse(chars, left, index - 1)
            left = index + 1  
        }
        reverse(chars, left, chars.size -1)
        return String(chars)
    }
    
    fun reverse(chars : CharArray, left : Int, right : Int){
        var l = left
        var r = right
        while(l < r){
            var temp = chars[l]
            chars[l++] = chars[r]
            chars[r--] = temp
        }
    }
}