package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

// 메모리 초과..

val array = IntArray(1000000)

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    var n = readLine().toInt()
    while (n != 0) {
        val st = StringTokenizer(readLine())
        for (i in 0 until n) {
            array[i] = st.nextToken().toInt()
        }
        reverse(n)
        val result = getResult(n)
        println(getResult(result.first, result.second).first)
        n = readLine().toInt()
    }
}

fun reverse(length: Int) {
    for (i in 0 until length / 2) {
        val temp = array[i]
        array[i] = array[length - 1 - i]
        array[length - 1 - i] = temp
    }
}

fun getResult(n: Int, sum: Int = 0): Pair<Int, Int> {
    var l = 0
    var s = sum
    for (i in 0 until n) {
        s += array[i]
        if (s >= 0) {
            array[l++] = s
            s = 0
        }
    }
    return Pair(l, s)
}