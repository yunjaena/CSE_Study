package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val l = st.nextToken().toInt()
    val letter = readLine()
    val chess = Array(m) {
        val s = readLine()
        CharArray(n) {
            s[it]
        }
    }

    println(letter.length)

}