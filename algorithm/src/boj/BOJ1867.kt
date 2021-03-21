package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val d = IntArray(501){0}
private var isVisited = BooleanArray(501){false}
private val a = Array(501){ mutableListOf<Int>()}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    var st = StringTokenizer(readLine())
    var n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
   for(i in 0 until k){
        st = StringTokenizer(readLine())
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()
        a[x].add(y)
    }

    var answer = 0

    for(i in 1 .. n){
        isVisited = BooleanArray(501){false}
        if(dfs(i)){
            answer++
        }
    }

    println(answer)
}

private fun dfs(x: Int): Boolean {
    (0 until a[x].size).forEach { i ->
        val y = a[x][i]

        if (isVisited[y])
            return@forEach

        isVisited[y] = true

        if (d[y] == 0 || dfs(d[y])) {
            d[y] = x
            return true
        }
    }
    return false
}