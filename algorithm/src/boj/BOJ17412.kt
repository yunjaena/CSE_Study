package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.min


private var N: Int = 0
private var P: Int = 0
private const val MAX = 401
private var capacity: Array<IntArray> = Array(MAX) { IntArray(MAX) { 0 } }
private var flow: Array<IntArray> = Array(MAX) { IntArray(MAX) { 0 } }
private var destination: IntArray = IntArray(MAX) { -1 }
private var graph: Array<MutableList<Int>> = Array(MAX) { mutableListOf<Int>() }
private var minFlow: Int = Int.MAX_VALUE
private var result = 0

/**
 * 애드모든 카프 알고리즘
 * 용량이 정해져 있지 않으므로 모두 1로 설정
 * https://blog.naver.com/ndb796/221237111220
 */


fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    N = st.nextToken().toInt()
    P = st.nextToken().toInt()
    for (i in 0 until P) {
        st = StringTokenizer(readLine())
        val start = st.nextToken().toInt()
        val end = st.nextToken().toInt()
        graph[start].add(end)
        graph[end].add(start)
        capacity[start][end] = 1
    }
    maxFlow(1, 2)
    print("$result")
}

fun maxFlow(start: Int, end: Int) {
    while (true) {
        destination = IntArray(MAX) { -1 }
        val queue = LinkedList<Int>()
        queue.push(start)
        while (!queue.isEmpty()) {
            val x = queue.pop()
            for (y in graph[x]) {
                // 방문하지 않은 노드 중에 용량이 남는 경우
                if (capacity[x][y] - flow[x][y] > 0 && destination[y] == -1) {
                    queue.push(y)

                    // 경로를 기억한다.
                    destination[y] = x

                    // 도착지에 도달한 경우 break
                    if (y == end)
                        break
                }
            }
        }

        // 모든 경로를 찾으면 destination[end] = -1 이므로 탈출
        if (destination[end] == -1)
            break

        minFlow = Int.MAX_VALUE
        var index = end
        while (index != start) {
            // 자기 자신 이전의 용량 값에서 자기 자신 이전값의 유량을 뺀 값을 비교
            minFlow = min(minFlow, capacity[destination[index]][index] - flow[destination[index]][index])
            index = destination[index]
        }

        index = end
        while (index != start) {
            // 최소 유량 만큼 추가해준다.
            flow[destination[index]][index] += minFlow
            flow[index][destination[index]] -= minFlow
            index = destination[index]
        }
        result += minFlow
    }
}