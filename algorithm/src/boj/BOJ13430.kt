package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private const val MOD = 1_000_000_007
fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val st = StringTokenizer(readLine())
    val k = st.nextToken().toInt()
    val n = st.nextToken().toLong()
    var a = Array(k + 2) { IntArray(k + 2) { 0 } }
    for (i in a.indices) {
        for (j in 0..i) {
            a[i][j] = 1
        }
    }

    val matrix = (a pow (n - 1))[k + 1]
    var answer = 0
    for (i in matrix) {
        answer += i
        answer = answer.rem(MOD)
    }
    println(answer)
}

infix fun Array<IntArray>.times(array: Array<IntArray>): Array<IntArray> {
    val matrix = Array(this.size) { IntArray(array[0].size) { 0 } }
    for (i in this.indices) {
        for (j in array.indices) {
            var sum = 0L
            for (k in this.indices) {
                sum += this[i][k].toLong() * array[k][j].toLong()
                sum = sum.rem(MOD)
            }
            matrix[i][j] += sum.toInt()
        }
    }
    return matrix
}

infix fun Array<IntArray>.pow(num: Long): Array<IntArray> {
    var sourceMatrix = this
    var pow = num
    var matrix = Array(this.size) { IntArray(this.size) { 0 } }
    for (i in this.indices) {
        matrix[i][i] = 1
    }
    while (pow > 0) {
        if (pow.rem(2) == 1L) {
            matrix = matrix times sourceMatrix
        }
        pow /= 2
        sourceMatrix = sourceMatrix times sourceMatrix
    }
    return matrix
}