package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.max

private var dp = Array(13) { IntArray(2000) { 0 } }
private var arr = Array(13) { CharArray(13) { ' ' } }
private var n = 0
private var m = 0

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var c = readLine().toInt()
    while (c-- > 0) {
        val st = StringTokenizer(readLine())
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()

        for (y in 1..n) {
            val s = readLine()
            for (x in 1..m) {
                arr[y][x] = s[x - 1]
            }
        }

        for (i in 1..n) {
            for (j in 0 until (1 shl m)) {
                for (k in 0 until (1 shl m)) {
                    dp[i][j] = max(dp[i][j], dp[i - 1][k] + chk(j, k, i))
                }
            }
        }

        var ans = 0

        for (j in 1..(1 shl m)) {
            ans = max(ans, dp[n][j])
        }

        println(ans)

        dp = Array(13) { IntArray(2000) { 0 } }
        arr = Array(13) { CharArray(13) { ' ' } }
    }
}

fun chk(now: Int, pre: Int, i: Int): Int {
    var result = 0
    for (seat in 1..m) {
        if ((now and (1 shl seat - 1)) == 0) continue
        if (arr[i][seat] == 'x') return 0
        if (seat >= 2) {
            if ((now and (1 shl seat - 2)) != 0) return 0
            if ((pre and (1 shl seat - 2)) != 0) return 0
        }
        if ((now and (1 shl seat)) != 0) return 0
        if ((pre and (1 shl seat)) != 0) return 0

        result += 1
    }
    return result
}