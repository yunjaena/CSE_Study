package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val N = st.nextToken().toInt()
    val M = st.nextToken().toInt()
    val linkCount = IntArray(N + 1) { 0 }
    val graph = arrayListOf<ArrayList<Int>>()

    for (i in 0..N) {
        graph.add(arrayListOf())
    }

    for (i in 0 until M) {
        st = StringTokenizer(readLine())
        val v1 = st.nextToken().toInt()
        val v2 = st.nextToken().toInt()
        graph[v1].add(v2)
        linkCount[v2]++
    }

    graph.topologicalSort(linkCount) {
        print("$it ")
    }
}

private inline fun ArrayList<ArrayList<Int>>.topologicalSort(linkCount: IntArray, printFormat: (Int) -> Unit) {
    val queue = LinkedList<Int>()

    for (i in 1 until linkCount.size) {
        if (linkCount[i] == 0) {
            queue.add(i)
        }
    }

    for (i in 0 until linkCount.size - 1) {
        val point = queue.remove()
        printFormat(point)

        for (nextPoint in this[point]) {
            linkCount[nextPoint]--

            if (linkCount[nextPoint] == 0)
                queue.add(nextPoint)
        }
    }
}