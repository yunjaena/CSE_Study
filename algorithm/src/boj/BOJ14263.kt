package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashSet

private var n: Int = 0
private var m: Int = 0
private lateinit var grid: Array<CharArray>

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    val hashSet = HashSet<Char>()
    grid = Array(n) {
        val s = readLine()
        CharArray(m) { s[it] }
    }

    grid.forEach {
        it.forEach {
            if(it != '.')
            hashSet.add(it)
        }
    }

    hashSet.forEach {
        print(it)
    }


}