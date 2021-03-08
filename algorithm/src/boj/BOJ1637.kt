package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


private lateinit var numberArray: Array<Numbers>
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    var ans = -1L
    var st: StringTokenizer
    numberArray = Array(n) {
        st = StringTokenizer(readLine())
        Numbers(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt())
    }

    var left = 0L
    var right = Int.MAX_VALUE.toLong()

    while (left <= right) {
            val mid = (left + right) / 2
            if (getNumber(mid, n) % 2 == 0L) {
                left = mid + 1
            } else {
                ans = mid
                right = mid - 1
            }
    }

    if (ans == -1L) {
        print("NOTHING")
    } else {
        print("$ans ${getNumber(ans, n) - getNumber(ans - 1, n)}")
    }


}

private fun getNumber(mid: Long, n: Int): Long {
    if (mid == 0L) return 0L

    var sum = 0L

    for (i in 0 until n) {
        var num = 0L
        var result = Math.min(numberArray[i].c.toLong(), mid) - numberArray[i].a
        num = if (result < 0) 0 else result / numberArray[i].b + 1
        sum += num
    }
    return sum
}

private class Numbers(
        val a: Int,
        val c: Int,
        val b: Int,
)