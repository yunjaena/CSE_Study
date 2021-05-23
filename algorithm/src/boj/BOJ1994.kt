package boj

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var n = readLine().toInt()
    var num = IntArray(n) { readLine().toInt() }
    var isUsed = Array(2003) { BooleanArray(2003) { false } }
    num.sort()
    var ans = 1
    var cur = num[0]
    var cnt = 1
    for (i in 1 until n) {
        if (num[i] == cur) cnt++
        else {
            if (cnt > ans) ans = cnt
            cur = num[i]
            cnt = 1
        }
    }

    if (cnt > ans) ans = cnt
    num = num.distinct().toIntArray()
    n = num.size

    for (i in 0 until n) {
        for (j in i + 1 until n) {
            if (isUsed[i][j]) continue
            isUsed[i][j] = true
            var count = 2
            val diff = num[j] - num[i]
            var value = num[j] + diff
            var beforeIdx = j
            while (true) {
                val idx: Int = num.lowerBound(0, num.size - 1, value)
                if (num[idx] != value) break
                isUsed[beforeIdx][idx] = true
                beforeIdx = idx
                value += diff
                count++
            }
            if (count > ans) ans = count
        }
    }
    print(ans)
}

private fun IntArray.lowerBound(low: Int, high: Int, value: Int): Int {
    var lo = low
    var hi = high
    var mid: Int = high
    while (lo < hi) {
        mid = (lo + hi) shr 1
        if (mid == high)
            return high

        if (this[mid] < value) { // value보다 작은 값을 만난 경우
            lo = mid + 1            // 탐색해야 하는 범위가 뒤쪽에 있다고 가정
        } else {                    // value보다 크거나 같은 값을 만난 경우
            hi = mid                // 탐색해야 하는 범위가 안쪽에 있다고 가정
        }
    }

    return lo                     // 맨 왼쪽 범위가 처음 나타나는 위치를 의미
}