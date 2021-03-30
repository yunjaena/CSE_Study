package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

private const val MAX = 101
private var index: Int = 0
private val cost = IntArray(MAX) { 0 }
private val p = IntArray(MAX) { 0 }
private val v = Array(MAX) { ArrayList<Int>() }
private val scc: ArrayList<ArrayList<Int>> = ArrayList()
private val stack = Stack<Int>()
private val isVisited = BooleanArray(MAX) { false }

private fun dfs(now: Int): Int {
    p[now] = ++index
    stack.push(now)
    var parent = p[now]

    for (i in v[now]) {

        if (p[i] == 0)
            parent = Math.min(parent, dfs(i))
        else if (!isVisited[i])
            parent = Math.min(parent, p[i])
    }

    if (parent == p[now]) {
        val graph = ArrayList<Int>()

        while (true) {
            val top = stack.pop()
            isVisited[top] = true
            graph.add(top)
            if (top == now) break
        }

        graph.sort()
        scc.add(graph)
    }
    return parent
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    var st = StringTokenizer(readLine())

    for (i in 1 .. n)
        cost[i] = st.nextToken().toInt()

    for (i in 1..n) {
        val s = readLine()
        s.forEachIndexed { index: Int, c: Char ->
            if (c == '1')
                v[i].add(index + 1)
        }
    }
    for (i in 1..n) {
        if (!isVisited[i]) {
            dfs(i)
        }
    }

    var answer = 0

    for (i in scc) {
        var min = Int.MAX_VALUE
        for (j in i) {
            min = Math.min(min, cost[j])
        }
        answer += min
    }

    println(answer)

}