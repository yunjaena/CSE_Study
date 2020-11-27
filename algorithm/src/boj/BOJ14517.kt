package boj

import java.io.BufferedReader
import java.io.InputStreamReader

private const val MOD: Int = 10_007

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val s = " " + readLine()
    var sum = 0
    val dp = Array(1002) { IntArray(1002) { 0 } }
    for (i in 1..s.length) {
        for (j in 1..s.length) {
            dp[i][j] = dp[i - 1][j]
        }

        for (j in i until s.length) {
            if (s[i] == s[j]) {
                dp[i][j] += 1
                // k = (1 ~ i - 1, j ~ len)까지 탐색한다. (현재 i, j에서 뻗어나갈 수 있는 모든 팰린드롬을 구해줌.)
                for (k in j + 1 until s.length) {
                    dp[i][j] += dp[i - 1][k]
                    dp[i][j] %= MOD
                    // print(dp, s.length)
                }
            }
        }

    }

    for (i in 1..s.length)
        sum += dp[s.length][i]

    println("${sum.rem(MOD)}")
}

fun print(array: Array<IntArray>, len : Int) {
    for (y in 0..len) {
        for (x in 0..len) {
            print("${array[y][x]} ")
        }
        println()
    }
    println()
}

/*
" " + abb
1  2  3  12  13  23  123
a, b, b, ab, ab, bb, abb

1 1 1 2 1 3
|
2 1 2 2 2 3
|
3 1 3 2 3 3
|
4 1 4 2 4 3 => sum

1 : 1 => a
2 : 2 => b
2 : 3 => bb
3 : 3 -> b

*/