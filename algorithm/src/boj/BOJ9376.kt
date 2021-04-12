package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var map: Array<CharArray>
private val dir: Array<Array<Int>> = arrayOf(
        arrayOf(-1, 0),
        arrayOf(1, 0),
        arrayOf(0, -1),
        arrayOf(0, 1)
)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var n = readLine().toInt()
    while (n-- > 0) {
        val st = StringTokenizer(readLine())
        val h = st.nextToken().toInt()
        val w = st.nextToken().toInt()

        map = Array(h) {
            val s = readLine()
            CharArray(w) { s[it] }
        }

    }
}