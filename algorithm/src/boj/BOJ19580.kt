package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var a: Array<Pair<Long, Long>>
private lateinit var b: Array<Pair<Long, Long>>

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    a = Array(n) {
        st = StringTokenizer(readLine())
        val first = st.nextToken().toLong()
        val second = st.nextToken().toLong()
        Pair(second, first)
    }

    b = Array(m) {
        st = StringTokenizer(readLine())
        Pair(st.nextToken().toLong(), st.nextToken().toLong())
    }

    a.sortWith{ a, b ->
        when (a.first) {
            b.first -> {
                b.second.compareTo(a.second)
            }
            else -> {
                b.first.compareTo(a.first)
            }
        }
    }

    b.sortWith{ a, b ->
        when (a.first) {
            b.first -> {
                b.second.compareTo(a.second)
            }
            else -> {
                b.first.compareTo(a.first)
            }
        }
    }


    val pq = PriorityQueue<Long>(Collections.reverseOrder())

    var pivot = 0
    var answer = 0

    for (i in 0 until m) {
        while (pivot < n && a[pivot].first >= b[i].first){
            pq.offer(a[pivot++].second)
        }

        while(!pq.isEmpty() && pq.peek() > b[i].first){
            pq.poll()
        }

        while (!pq.isEmpty() && b[i].second > 0) {
            b[i] = b[i].copy(second = b[i].second - 1)
            pq.poll()
            answer++
        }
    }

    println(answer)
}