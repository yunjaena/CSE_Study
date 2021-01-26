package boj

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    while (true) {
        val arr: LongArray = readLine().split(" ").map { it.toLong() }.toLongArray()
        if (arr.isEmpty() || arr[0] == 0L) return
        println(arr.getMax(0, arr.size - 1))
    }
}

private fun LongArray.getMax(left: Int, right: Int): Long {
    if (left == right)
        return this[left]

    val mid = (left + right) / 2

    var area = getMax(left, mid).coerceAtLeast(getMax(mid + 1, right))

    var start = mid
    var end = mid + 1
    var height = this[start].coerceAtMost(this[end])

    area = area.coerceAtLeast(height * 2)

    while (left < start || end < right) {
        if (left < start && end < right) {
            height = if (this[start - 1] < this[end + 1])
                height.coerceAtMost(this[++end])
            else
                height.coerceAtMost(this[--start])
        } else if (left < start) {
            height = height.coerceAtMost(this[--start])
        } else if (end < right) {
            height = height.coerceAtMost(this[++end])
        }
        area = area.coerceAtLeast(height * (end - start + 1))
    }
    return area
}