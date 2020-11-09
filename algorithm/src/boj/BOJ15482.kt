package boj

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val a = readLine()
    val b = readLine()
    println(a.lcs(b))

}

fun String.lcs(b: String): Int {
    val lcs = Array(this.length + 1) { IntArray(b.length + 1) { 0 } }
    for (y in 1..this.length) {
        for (x in 1..b.length) {
            if (this[y - 1] == b[x - 1]) {
                lcs[y][x] = lcs[y - 1][x - 1] + 1
            } else {
                lcs[y][x] = lcs[y][x - 1].coerceAtLeast(lcs[y - 1][x])
            }
        }
    }
    return lcs[lcs.size - 1][lcs[0].size - 1]
}
