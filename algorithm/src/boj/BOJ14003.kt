package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var a = IntArray(1000001) { 0 }
private var myLis = IntArray(1000001) { 0 }
private var lis = IntArray(1000001) { 0 }

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val st = StringTokenizer(readLine())
    st.iterator().withIndex().forEach {
        a[it.index] = it.value.toString().toInt()
    }

    lis[0] = a[0]
    myLis[0] = 1

    var len = 1

    for (i in 0 until n) {
        if (a[i] > lis[len - 1]) {
            lis[len++] = a[i]
            myLis[i] = len
            continue
        }

        val idx = lis.lowerBound(0, len, a[i])
        lis[idx] = a[i]
        myLis[i] = idx + 1
    }

    println(len)

    val v = arrayListOf<Int>()
    for (i in n - 1 downTo 0) {
        if (myLis[i] == len) {
            v.add(a[i])
            len--
        }
    }

    v.reverse()
    v.forEach {
        print("$it ")
    }
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