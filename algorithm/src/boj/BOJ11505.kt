package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.ceil
import kotlin.math.log2

val MOD = 1_000_000_007

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    var split = readLine().split(" ")
    val N = split[0].toInt()
    var M = split[1].toInt()
    var K = split[2].toInt()
    val numbers = Array(N) { readLine().toLong() }
    val tree: Array<Long> = Array(treeSize(N)) { 0L }
    initTree(numbers, tree, 1, 0, N - 1)
    while (M + K > 0) {
        split = readLine().split(" ")
        if (split[0].toInt() == 1) {
            val index = split[1].toInt()
            val number = split[2].toLong()
            updateTree(tree, 1, 0, N - 1, index -1 , number)
            M--
        } else {
            val left = split[1].toInt()
            val right = split[2].toInt()
            println(findMultiply(tree, 1, 0, N - 1, left -1 , right-1).rem(MOD))
            K--
        }

    }


}

private fun treeSize(n: Int) = 1.shl(ceil(log2(n.toFloat())).toInt() + 1)

private fun initTree(array: Array<Long>, tree: Array<Long>, node: Int, start: Int, end: Int): Long {
    if (start == end) {
        tree[node] = array[start].rem(MOD)
        return tree[node]
    }

    val mid = (start + end) / 2
    tree[node] = (initTree(array, tree, node * 2, start, mid) * initTree(array, tree, node * 2 + 1, mid + 1, end)).rem(MOD)
    return tree[node]
}

private fun findMultiply(tree: Array<Long>, node: Int, start: Int, end: Int, left: Int, right: Int): Long {
    if (left > end || right < start)
        return 1

    if (left <= start && end <= right)
        return tree[node]

    val mid = (start + end) / 2
    return (findMultiply(tree, node * 2, start, mid, left, right) * findMultiply(tree, node * 2 + 1, mid + 1, end, left, right)).rem(MOD)
}

private fun updateTree(tree: Array<Long>, node: Int, start: Int, end: Int, index: Int, num: Long): Long {
    if (index > end || index < start)
        return tree[node]

    if (start == end) {
        tree[node] = num
        return tree[node]
    }

    val mid = (start + end) / 2
    tree[node] =  (updateTree(tree, node * 2, start, mid, index, num) * updateTree(tree, node * 2 + 1, mid + 1, end, index, num)).rem(MOD)
    return tree[node]
}