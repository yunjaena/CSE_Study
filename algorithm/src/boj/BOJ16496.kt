package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.Comparator

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    var n = readLine().toInt()
    var st = StringTokenizer(readLine())
    val arr = LongArray(n){st.nextToken().toLong()}

    val sortArray = arr.sortedWith(Comparator {first, second ->
        (second.toString() + first.toString()).compareTo(first.toString() + second.toString())
    })

    var ans = ""

    for(s in sortArray){
        ans += s.toString()
    }

    if(ans.length > 1 && ans[0] == '0'){
        ans = "0"
    }

    println(ans)
}