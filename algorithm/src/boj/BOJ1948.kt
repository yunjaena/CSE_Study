package boj

// https://dbstndi6316.tistory.com/78

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


private lateinit var graph: Array<ArrayList<Edge>>
private lateinit var reverseGraph: Array<ArrayList<Edge>>
private lateinit var degree: IntArray
private lateinit var meetTime: IntArray
private lateinit var isVisited: BooleanArray
private var maxTime: Int = 0
private var maxLoad: Int = 0

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().toInt()
    val M = readLine().toInt()
    graph = Array(N + 1) { arrayListOf() }
    reverseGraph = Array(N + 1) { arrayListOf() }
    degree = IntArray(N + 1) { 0 }
    meetTime = IntArray(N + 1) { 0 }
    isVisited = BooleanArray(N + 1) { false }
    var st: StringTokenizer

    for (i in 0 until M) {
        st = StringTokenizer(readLine())
        val start = st.nextToken().toInt()
        val end = st.nextToken().toInt()
        val time = st.nextToken().toInt()
        graph[start].add(Edge(end, time))
        reverseGraph[end].add(Edge(start, time))
        degree[end]++
    }

    st = StringTokenizer(readLine())
    val startPoint = st.nextToken().toInt()
    val endPoint = st.nextToken().toInt()

    topologicalSort(startPoint, endPoint)

    println(maxTime)
    println(maxLoad)
}

private fun topologicalSort(startPoint: Int, endPoint: Int) {
    val queue = LinkedList<Int>()
    queue.push(startPoint)
    while (!queue.isEmpty()) {
        val edge = queue.pop()
        for (i in graph[edge].withIndex()) {
            val nextEdge = Edge(i.value.node, i.value.time)
            if (meetTime[nextEdge.node] <= nextEdge.time + meetTime[edge]) {
                meetTime[nextEdge.node] = nextEdge.time + meetTime[edge]
            }

            degree[nextEdge.node]--

            if (degree[nextEdge.node] == 0)
                queue.push(nextEdge.node)
        }
    }

    queue.push(endPoint)
    while (!queue.isEmpty()) {
        val edge = queue.pop()
        for (i in reverseGraph[edge].withIndex()) {
            val nextEdge = Edge(i.value.node, i.value.time)
            if (nextEdge.time == meetTime[edge] - meetTime[nextEdge.node]) {
                maxLoad++
                if (!isVisited[nextEdge.node]) {
                    queue.push(nextEdge.node)
                    isVisited[nextEdge.node] = true
                }
            }
        }
    }

    maxTime = meetTime[endPoint]

}

data class Edge(val node: Int, val time: Int)