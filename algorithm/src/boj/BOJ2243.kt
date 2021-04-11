package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private const val MAX = 1_000_001
private lateinit var tree: IntArray

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var n = readLine().toInt()
    tree = IntArray(MAX * 4) { 0 }
    while (n-- > 0) {
        val input = StringTokenizer(readLine())
        when (input.nextToken().toInt()) {
            1 -> {
                val favor = query(1, input.nextToken().toInt(), 1, MAX)
                println(favor)
                update(1, favor, -1, 1, MAX)
            }
            2 -> {
                update(1, input.nextToken().toInt(), input.nextToken().toInt(), 1, MAX)
            }
        }
    }
}

private fun update(index: Int, find: Int, diff: Int, start: Int, end: Int) {
    if (find < start || find > end) return

    tree[index] += diff
    if (start == end)
        return
    val mid = (start + end) / 2
    update(index * 2, find, diff, start, mid)
    update(index * 2 + 1, find, diff, mid + 1, end)
}

private fun query(index: Int, find: Int, start: Int, end: Int): Int {
    // println("index : $index find $find start $start end $end")
    if (start == end)
        return start
    val mid = (start + end) / 2
    return when {
        find <= tree[index * 2] -> query(index * 2, find, start, mid)
        else -> query(index * 2 + 1, find - tree[index * 2], mid + 1, end)
    }

}