package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val n = readLine().toInt()
    val st = StringTokenizer(readLine())
    val arr = IntArray(n){st.nextToken().toInt()}
    var sum = 0
    arr.sort()

    arr.forEachIndexed { index, value ->
        sum += value
        if(sum < index * (index + 1) / 2){
            println(-1)
            return@with
        }
    }
    when{
        (sum == n * (n - 1) / 2) -> println(1)
        else -> println(-1)
    }
}