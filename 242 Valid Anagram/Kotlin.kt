
//solution 1 - 
//2 strings have the same characters with the same count, we could count each charcter for each, and compare those counts,
//or we can create a map for one string and remove a count for every character in the second string
//time O(s+t)
//space O(s)
fun isAnagram2(s: String, t: String): Boolean {
    var seen = hashMapOf<Char, Int>()
    s.forEach { char -> 
        if(seen.contains(char)){
            seen[char] = seen.getValue(char) + 1   
        }
        else{
            seen.put(char, 1)
        }
    }
    t.forEach { char2 ->
        if(!seen.contains(char2)){
            return false
        }
        seen[char2] = seen.getValue(char2) - 1    
        if(seen[char2] == 0){
            seen.remove(char2)
        }
    }
    return !seen.any()
}

//solution 2, if they are anagrams they are going to have the same sorted string. 
//Because of this, we can sort both strings and compare them
//time O(sLogs + tLogt) - because of sorting both strings
//space O(s + t) - for copying the arrays into CharArrays
fun isAnagram(s: String, t: String): Boolean {
    var sortedS = s.toCharArray()
    sortedS.sort()
    var sortedT = t.toCharArray()
    sortedT.sort()
    return sortedS contentEquals sortedT
}

//note: although on paper solution 1 has a better time and space complexity, I could not get it to out perform solution 2
