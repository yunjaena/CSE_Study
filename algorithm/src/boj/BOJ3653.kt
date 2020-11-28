package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.util.*


fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    var t = readLine().toInt()
    val sb = StringBuilder()
    while (t-- > 0) {
        var st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()
        val max = n + m
        val tree = LongArray(max * 4) { 0L }
        val arr = IntArray(n + 1) { 0 }
        var num = 0
        for (i in n downTo 1) {
            arr[i] = num++
        }

        for (i in 1..n) {
            updateTree(tree, 1, 0, max, arr[i], 1)
        }

        var c = n
        st = StringTokenizer(readLine())
        while (st.hasMoreTokens()) {
            val s = st.nextToken().toInt()
            sb.append(find(tree, 1, 0, max, arr[s] + 1, max)).append(" ")
            updateTree(tree, 1, 0, max, arr[s], 0)
            arr[s] = c++
            updateTree(tree, 1, 0, max, arr[s], 1)
        }
        sb.append("\n")
    }
    println(sb.toString())
}

private fun find(tree: LongArray, node: Int, start: Int, end: Int, left: Int, right: Int): Long {
    if (left > end || right < start)
        return 0

    if (left <= start && end <= right)
        return tree[node]

    val mid = (start + end) / 2
    return (find(tree, node * 2, start, mid, left, right) + find(tree, node * 2 + 1, mid + 1, end, left, right))
}

private fun updateTree(tree: LongArray, node: Int, start: Int, end: Int, index: Int, num: Long): Long {
    if (index > end || index < start)
        return tree[node]

    if (start == end) {
        tree[node] = num
        return tree[node]
    }

    val mid = (start + end) / 2
    tree[node] = (updateTree(tree, node * 2, start, mid, index, num) + updateTree(tree, node * 2 + 1, mid + 1, end, index, num))
    return tree[node]
}