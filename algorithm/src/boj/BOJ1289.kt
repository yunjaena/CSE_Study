package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.cos

private val tree = Array(100_001) { mutableListOf<Pair<Int, Int>>() }
private val subtree = LongArray(100_001) { 0 }
private val isVisited = BooleanArray(100_001) { false }
private const val MOD = 1_000_000_007
private var answer = 0L

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    for (i in 0 until n - 1) {
        val st = StringTokenizer(readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val w = st.nextToken().toInt()

        tree[a].add(Pair(b, w))
        tree[b].add(Pair(a, w))
    }

    dfs(1)
    print(answer)
}

private fun dfs(node: Int) {
    isVisited[node] = true
    for (i in tree[node]) {
        val cost = i.first
        val nextNode = i.second

        var sum = 0L
        if (isVisited[nextNode]) continue
        dfs(nextNode)


        sum += cost.toLong()
        sum = sum.rem(MOD)
        sum += (cost.toLong() * subtree[nextNode]).rem(MOD)

         answer  += (cost.toLong() * subtree[nextNode]).rem(MOD)
        answer %= MOD
        answer += sum
        answer %= MOD
        answer += (((cost.toLong() * subtree[node]).rem(MOD)) * subtree[nextNode]).rem(MOD)
        answer %= MOD
        subtree[node] = subtree[node].plus(sum)
        subtree[node] = subtree[node].rem(MOD)
    }

}