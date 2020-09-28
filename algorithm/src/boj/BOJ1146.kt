package boj

import java.io.BufferedReader
import java.io.InputStreamReader

// https://wonjaek.tistory.com/131
// https://www.youtube.com/watch?v=s7hv3ziOc7I
val arr = Array(102) { Array(102) { IntArray(2) } }
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().toInt()
    var sum = 0
    if (N == 1) {
        println(1)
        return
    }

    for (i in 0..100) {
        for (j in 0..100) {
            arr[i][j][0] = -1
            arr[i][j][1] = -1
        }
    }

    arr[0][0][0] = 1
    arr[0][0][1] = 1

    for (i in 1..N) {
        for (j in 1..N) {
            if (i == j) continue
            val left = j - 1 - if (i < j) 1 else 0
            val right = N - j - if (i > j) 1 else 0
            sum += solve(left, right, if (i > j) 1 else 0)
            sum = sum.rem(1000000)
        }
    }
    println(sum)
}

fun solve(left: Int, right: Int, order: Int): Int {
    var sum = 0
    if (arr[left][right][order] != -1) {
        return arr[left][right][order]
    }
    if (order == 0) {
        for (i in 1..left) {
            sum += solve(left - i, right + (i - 1), 1)
            sum = sum.rem(1000000)
        }
    } else {
        for (i in 1..right) {
            sum += solve(left + (i - 1), right - i, 0)
            sum = sum.rem(1000000)
        }
    }
    arr[left][right][order] = sum
    return arr[left][right][order]
}