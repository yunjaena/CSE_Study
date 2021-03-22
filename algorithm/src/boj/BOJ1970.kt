package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val st = StringTokenizer(readLine())
    val table = IntArray(2001) { 0 }
    for (i in 0 until n) {
        table[i] = st.nextToken().toInt()
    }
    val dp = Array(2001) { IntArray(2001) { 0 } }
    for (k in 2..n) {
        for (i in 0 until n) {
            val j = i + k - 1
            val l = j % n
            if (table[i] == table[l] && dp[i][l] < dp[(i + 1) % n][(l + n - 1) % n] + 1) {
                dp[i][l] = dp[(i + 1) % n][(l + n - 1) % n] + 1
            }

            for (m in i until j) {
                if (dp[i][l] < dp[i][m % n] + dp[(m + 1) % n][l])
                    dp[i][l] = dp[i][m % n] + dp[(m + 1) % n][l]
            }
        }
    }

    print(dp[0][n - 1])
}