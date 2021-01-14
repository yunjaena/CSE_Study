package boj

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val s = readLine()
    for (i in 1 until s.length step 2) {
        check(s.substring(0, i + 1))
    }
}

fun check(s: String){
    var split = 2
    var checkLength = s.length / split++
    val hashSet = HashSet<String>()
    while(checkLength >  0) {
        hashSet.clear()
        hashSet.addAll(s.chunked(checkLength))

        if(hashSet.size == 1){
            println("${s.length} ${s.length/checkLength}")
            return
        }

        if(checkLength == 1) return
        checkLength = s.length / split++
    }
}