package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


private const val MAX = 100020
private val front = Array(MAX) { mutableListOf<Int>() }
private val reverse = Array(MAX) { mutableListOf<Int>() }
private var isVisited = BooleanArray(MAX) { false }
private val stack = Stack<Int>()
private val sccGroup = mutableListOf<MutableList<Int>>()
private var V: Int = 0
private var E: Int = 0

// https://blog.qwaz.io/problem-solving/scc%EC%99%80-2-sat
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    V = st.nextToken().toInt()
    E = st.nextToken().toInt()

    for (i in 0 until E) {
        st = StringTokenizer(readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        front[a].add(b)
        reverse[b].add(a)
    }

    solve()
}

private fun solve() {
    for (node in 1..V) {
        if (!isVisited[node]) {
            frontDfs(node)
        }
    }

    isVisited = BooleanArray(MAX) { false }

    while (!stack.isEmpty()) {
        val node = stack.pop()
        if (!isVisited[node]) {
            sccGroup.add(mutableListOf())
            reverseDfs(node)
        }
    }

    for (group in sccGroup) {
        group.sort()
    }
    sccGroup.sortWith { s1, s2 ->
        s1[0].compareTo(s2[0])
    }

    println(sccGroup.size)
    for (group in sccGroup) {
        for (element in group) {
            print("$element ")
        }
        println("-1")
    }
}

private fun frontDfs(node: Int) {
    isVisited[node] = true
    for (nextNode in front[node]) {
        if (!isVisited[nextNode]) {
            frontDfs(nextNode)
        }
    }
    stack.push(node)
}

private fun reverseDfs(node: Int) {
    isVisited[node] = true
    sccGroup[sccGroup.size - 1].add(node)

    for (nextNode in reverse[node]) {
        if (!isVisited[nextNode]) {
            reverseDfs(nextNode)
        }
    }
    stack.push(node)
}