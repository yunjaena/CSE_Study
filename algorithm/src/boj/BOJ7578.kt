package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var tree: LongArray
private lateinit var a: LongArray
private var h = IntArray(1000010) { 0 }


fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    a = LongArray(n + 10) { 0 }
    tree = LongArray(4 * n + 10) { 0 }

    readLine().also {
        StringTokenizer(it).iterator().withIndex().forEach { number ->
            h[number.value.toString().toInt()] = number.index + 1
        }
    }

    readLine().also {
        StringTokenizer(it).iterator().iterator().withIndex().forEach { number ->
            a[number.index + 1] = h[number.value.toString().toInt()].toLong()
        }
    }

    var ans = 0L

    for (i in 1..n) {
        val j = a[i].toInt()
        ans += sum(1, 1, n, j + 1, n)
        update(1, 1, n, j, 1)
    }

    println(ans)
}

private fun update(node: Int, start: Int, end: Int, idx: Int, diff: Int) {
    if (idx < start || idx > end) return
    tree[node] = tree[node] + diff
    if ((start xor end) > 0) {
        update(node * 2, start, (start + end) / 2, idx, diff)
        update(node * 2 + 1, (start + end) / 2 + 1, end, idx, diff)
    }
}

private fun sum(node: Int, start: Int, end: Int, left: Int, right: Int): Long {
    if (left > end || right < start) {
        return 0
    }
    return if (left <= start && end <= right) {
        tree[node]
    } else sum(node * 2, start, (start + end) / 2, left, right) + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right)
}