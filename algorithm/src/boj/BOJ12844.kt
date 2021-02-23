package boj

import java.io.BufferedReader
import java.io.InputStreamReader

private const val MAX = 1.shl(21)
private val tree = IntArray(MAX) { 1 }
private val result = IntArray(MAX) { 0 }

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var n: Int = readLine().toInt()
    var count = 0
    var pairList = Array(n){ i: Int ->  Pair(readLine().toInt(),i) }

    pairList.sortListPairDesc()
    tree.init()

    while(count < n){
        var currentIndex = pairList[count / 2].second

        result[count] = tree.sum(0, currentIndex - 1)
        tree.update(currentIndex, 0)
        count++

        if(count == n) break

        currentIndex = pairList[n - (count + 1) / 2].second

        result[count] - tree.sum(currentIndex + 1, n - 1)

        tree.update(currentIndex, 0)
        count++
    }

    for(i in 0 until n){
        println(result[i])
    }
}

private fun IntArray.init() {
    for (i in MAX / 2 - 1 downTo 0) {
        this[i] = this[i * 2] + this[i * 2 + 1]
    }
}

private fun IntArray.sum(start: Int, end: Int): Int {
    return this.sum(start, end, 1, 0, start - 1)
}

private fun IntArray.sum(start: Int, end: Int, node: Int, left: Int, right: Int): Int {
    if (end < left || right < start)
        return 0
    if (start <= left && right <= end)
        return this[node]

    val mid = (left + right) / 2
    return sum(start, end, node * 2, left, mid) + sum(start, end, node * 2 + 1, mid + 1, right)

}

private fun IntArray.update(index : Int, value : Int){
    this[MAX / 2 + index]  = value
    var i = (MAX / 2 + index)/2
    while (i > 0){
        this[i] = this[i * 2] + this[i * 2 + 1]
        i /= 2
    }
}

fun Array<Pair<Int, Int>>.sortListPairDesc(): List<Pair<Int, Int>> {
    return sortedWith(compareBy({ it.second }, { it.first })).asReversed()
}