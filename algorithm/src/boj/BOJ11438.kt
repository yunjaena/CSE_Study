package boj

import java.io.*
import java.util.*


private lateinit var tree: MutableList<MutableList<Int>>
private lateinit var depth: IntArray
private lateinit var parents: Array<IntArray>
private var k: Int = 0
private var M: Int = 0
private var N: Int = 0


fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st: StringTokenizer
    N = readLine().toInt()
    tree = ArrayList()
    for (i in 0 until N + 1) tree.add(ArrayList())
    for (i in 0 until N - 1) {
        st = StringTokenizer(readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()

        tree[a].add(b)
        tree[b].add(a)
    }
    var tmp = 1
    k = 0
    while (tmp <= N) {
        tmp = tmp shl 1
        k++
    }

    depth = IntArray(N + 1)
    parents = Array(N + 1) { IntArray(k) }

    dfs(1, 1)

    fill()

    M = readLine().toInt()
    with(BufferedWriter(OutputStreamWriter(System.out))){
    for (i in 0 until M) {
        st = StringTokenizer(readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val lca = lca(a, b)
            write(lca.toString())
            newLine()
        }
        flush()
        close()
    }
}

private fun lca(a: Int, b: Int): Int {
    var a = a
    var b = b
    if (depth[a] < depth[b]) {
        val temp = a
        a = b
        b = temp
    }

    for (i in k - 1 downTo 0) {
        if (Math.pow(2.0, i.toDouble()) <= depth[a] - depth[b]) {
            a = parents[a][i]
        }
    }
    if (a == b) return a

    for (i in k - 1 downTo 0) {
        if (parents[a][i] != parents[b][i]) {
            a = parents[a][i]
            b = parents[b][i]
        }
    }
    return parents[a][0]
}

private fun fill() {
    for (i in 1 until k) {
        for (j in 1..N) {
            parents[j][i] = parents[parents[j][i - 1]][i - 1]
        }
    }
}

private fun dfs(node: Int, cnt: Int) {
    depth[node] = cnt
    for (next in tree[node]) {
        if (depth[next] == 0) {
            dfs(next, cnt + 1)
            parents[next][0] = node
        }
    }
}