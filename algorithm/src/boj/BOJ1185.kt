package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private const val MAX = 10001
private var n: Int = 0
private var m: Int = 0
private lateinit var adj: MutableList<Country>
private var par = IntArray(MAX) { 0 }
private var W = IntArray(MAX) { 0 }

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    var answer = 1000
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    adj = mutableListOf()

    for (i in 1..n) {
        W[i] = readLine().toInt()
        answer = Math.min(answer, W[i])
    }


    for (i in 0 until m) {
        st = StringTokenizer(readLine())
        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        val w = st.nextToken().toInt()
        adj.add(Country(u, v, w * 2 + W[u] + W[v]))
    }

    adj.sort()
    for (i in 0 until adj.size) {
        if (merge(adj[i].u, adj[i].v)) {
            answer += adj[i].w
        }
    }

    println(answer)
}

private fun merge(u: Int, v: Int): Boolean {
    val u = find(u)
    val v = find(v)
    if (u == v) return false
    par[v] = u
    return true
}

private fun find(x: Int): Int {
    if (par[x] == 0) return x
    par[x] = find(par[x])
    return par[x]
}

private class Country(
        val u: Int,
        val v: Int,
        val w: Int,
) : Comparable<Country> {
    override fun compareTo(other: Country): Int {
        return w.compareTo(other.w)
    }
}