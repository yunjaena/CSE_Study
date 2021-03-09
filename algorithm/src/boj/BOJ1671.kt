package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private const val MAX = 51
private const val SAME = 0
private const val LARGER = 1
private const val SMALLER = 2
private const val NONE = -1
private var sharks = Array<Shark?>(MAX) { null }
private var d = IntArray(MAX) { 0 }
private var a = Array(MAX) { arrayListOf<Int>() }
private var isVisited = BooleanArray(MAX) { false }

private fun dfs(x: Int): Boolean {
    (0 until a[x].size).forEach { i ->
        val y = a[x][i]

        if (isVisited[y])
            return@forEach

        isVisited[y] = true

        if (d[y] == 0 || dfs(d[y])) {
            d[y] = x;
            return true
        }
    }
    return false
}

private fun sharkCompare(a: Shark, b: Shark): Int {
    if (a.size == b.size && a.speed == b.speed && a.intelligence == b.intelligence) {
        return SAME
    } else if (a.size >= b.size && a.speed >= b.speed && a.intelligence >= b.intelligence) {
        return LARGER
    } else if (a.size <= b.size && a.speed <= b.speed && a.intelligence <= b.intelligence) {
        return SMALLER
    }
    return NONE
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var answer = 0
    val n = readLine().toInt()
    for (i in 1 .. n) {
        val st = StringTokenizer(readLine())
        sharks[i] = Shark(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt())
    }

    for (i in 1 until n) {
        for (j in i + 1..n) {
            when (sharkCompare(sharks[i]!!, sharks[j]!!)) {
                SAME -> a[i].add(j)
                LARGER -> a[i].add(j)
                SMALLER -> a[j].add(i)
            }
        }
    }

    for (i in 0 until 2) {
        for (j in 1..n) {
            isVisited = BooleanArray(MAX) { false }
            if (dfs(j))
                answer++
        }
    }
    print(n - answer)

}

data class Shark(
        val size: Int,
        val speed: Int,
        val intelligence: Int,
)