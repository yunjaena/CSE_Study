package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

private var n = 0
private lateinit var isVisited: BooleanArray
private lateinit var d: IntArray
private lateinit var par: Array<IntArray>
private lateinit var qmax: Array<IntArray>
private lateinit var qmin: Array<IntArray>
private var nodeHashMap = HashMap<Int, ArrayList<RoadNode>>()

// https://devowen.com/274

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    n = readLine().toInt()
    isVisited = BooleanArray(n + 1) { false }
    d = IntArray(n + 1) { 0 }
    par = Array(n + 1) { IntArray(21) { 0 } }
    qmax = Array(n + 1) { IntArray(21) { 0 } }
    qmin = Array(n + 1) { IntArray(21) { 0 } }
    for (i in 0 until n - 1) {
        val st = StringTokenizer(readLine())

        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()

        if (!nodeHashMap.containsKey(a)) {
            nodeHashMap[a] = ArrayList()
        }
        nodeHashMap[a]?.add(RoadNode(b, c))

        if (!nodeHashMap.containsKey(b)) {
            nodeHashMap[b] = ArrayList()
        }
        nodeHashMap[b]?.add(RoadNode(a, c))
    }

    dfs(1, 0)
    f()
    val m = readLine().toInt()

    for (i in 0 until m) {
        val st = StringTokenizer(readLine())
        lca(st.nextToken().toInt(), st.nextToken().toInt())?.let {
            println("${it.dest} ${it.length}")
        }
    }

}

private fun dfs(here: Int, depth: Int) {
    isVisited[here] = true
    d[here] = depth
    for (there in nodeHashMap[here]!!) {
        if (isVisited[there.dest]) {
            continue
        }
        dfs(there.dest, depth + 1)
        par[there.dest][0] = here
        qmin[there.dest][0] = there.length
        qmax[there.dest][0] = there.length
    }
}

private fun f() {
    for (j in 1..20) {
        for (i in 1..n) {
            par[i][j] = par[par[i][j - 1]][j - 1]
            qmin[i][j] = Math.min(qmin[i][j - 1], qmin[par[i][j - 1]][j - 1])
            qmax[i][j] = Math.max(qmax[i][j - 1], qmax[par[i][j - 1]][j - 1])
        }
    }
}

private fun lca(tx: Int, ty: Int): RoadNode? {
    var x = tx
    var y = ty
    if (d[tx] > d[ty]) {
        y = tx
        x = ty
    }
    var rmin = Int.MAX_VALUE
    var rmax = Int.MIN_VALUE
    for (i in 20 downTo 0) {
        if (d[y] - d[x] >= 1 shl i) {
            rmin = Math.min(rmin, qmin[y][i])
            rmax = Math.max(rmax, qmax[y][i])
            y = par[y][i]
        }
    }
    if (x == y) return RoadNode(rmin, rmax)
    for (i in 20 downTo 0) {
        if (par[x][i] != par[y][i]) {
            rmin = Math.min(rmin, Math.min(qmin[x][i], qmin[y][i]))
            rmax = Math.max(rmax, Math.max(qmax[x][i], qmax[y][i]))
            x = par[x][i]
            y = par[y][i]
        }
    }
    rmin = Math.min(rmin, Math.min(qmin[x][0], qmin[y][0]))
    rmax = Math.max(rmax, Math.max(qmax[x][0], qmax[y][0]))
    return RoadNode(rmin, rmax)
}


private data class RoadNode(
        var dest: Int,
        var length: Int
)