package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.min

// https://www.crocus.co.kr/648
// https://www.crocus.co.kr/651?category=159837
// 세그먼트 트리

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    var s = readLine().split(" ")
    val N: Int = s[0].toInt()
    val M: Int =  s[1].toInt()
    val array: Array<Int> = Array(N) { 0 }
    val pairArray: Array<Pair<Int, Int>> = Array(M) { Pair(0, 0) }
    val tree: Array<Int> = Array(treeSize(N)) { 0 }


    for (i in 0 until N)
        array[i] = readLine().toInt()

    for (i in 0 until M) {
        s = readLine().split(" ")
        pairArray[i] = Pair(s[0].toInt(), s[1].toInt())
    }

    initTree(array, tree, 1, 0, N - 1)

    for (i in pairArray) {
        println(findMin(tree, 1, 0, N - 1, i.first - 1, i.second - 1))
    }
}

private fun treeSize(n: Int) = 1.shl(ceil(log2(n.toFloat())).toInt() + 1)

private fun initTree(array: Array<Int>, tree: Array<Int>, node: Int, start: Int, end: Int): Int {
    if (start == end) {
        tree[node] = array[start]
        return tree[node]
    }
    val mid = (start + end) / 2
    tree[node] = min(initTree(array, tree, node * 2, start, mid), initTree(array, tree, node * 2 + 1, mid + 1, end))
    return tree[node]
}

private fun findMin(tree: Array<Int>, node: Int, start: Int, end: Int, left: Int, right: Int): Int {
    if (left > end || right < start)
        return Int.MAX_VALUE

    if (left <= start && end <= right)
        return tree[node]

    val mid = (start + end) / 2
    return min(findMin(tree, node * 2, start, mid, left, right), findMin(tree, node * 2 + 1, mid + 1, end, left, right))
}