package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

val br = BufferedReader(InputStreamReader(System.`in`))
fun main(): Unit = with(br) {
    var N = readLine().toInt()
    while (N-- > 0) {
        solve()
    }
}

private fun solve(): Unit = with(br) {
    var st = StringTokenizer(readLine())
    val m = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    val arr = Array<Int>(510) { 0 }
    var i = 0
    var max = 0
    st = StringTokenizer(readLine())
    while (st.hasMoreTokens()) {
        arr[i] = st.nextToken().toInt()
        max = max.coerceAtLeast(arr[i])
        i++
    }

    var s = 0L
    var e = 1L

    while (!check(m, k, e, max, arr))
        e = e.shl(1)

    while (s + 1 < e) {
        var t = (e + s) / 2
        if (check(m, k, t, max, arr)) e = t
        else s = t
    }

    var seperate = Array(m) { false }
    var sum = 0
    var count = 1

    for (i in m - 1 downTo 0) {
        if (sum + arr[i] > e) {
            sum = arr[i]
            ++count
            seperate[i] = true
        } else
            sum += arr[i];
    }


    for (i in 0 until m) {
        if (count >= k) break
        if (!seperate[i]) {
            seperate[i] = true
            ++count
        }
    }

    for (i in 0 until m) {
        print("${arr[i]} ")
        if (seperate[i]) print("/ ")
    }
    println()

}


fun check(m: Int, k: Int, num: Long, max: Int, arr: Array<Int>): Boolean {
    if (num < max)
        return false

    var cnt = 1
    var sum = 0L

    for (i in 0 until m) {
        if (sum + arr[i] > num) {
            sum = arr[i].toLong()
            ++cnt
        } else
            sum += arr[i]
    }
    return cnt <= k
}
