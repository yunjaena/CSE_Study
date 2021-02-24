package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val inputs = readLine().split(" ")
    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, - 1, 1)
    val N = inputs[0].toInt()
    val M = inputs[1].toInt()
    val K = inputs[2].toInt()
    var answer = Int.MAX_VALUE
    val q = LinkedList<Node>()
    val map = Array(N + 1) { IntArray(M + 1) }
    val visited = Array(N + 1) { Array(M + 1) { BooleanArray(K + 1) } }
    for (r in 1..N) {
        val line = readLine().toCharArray()
        for (c in 1..M) {
            map[r][c] = line[c - 1] - '0'
        }
    }

    visited[1][1][0] = true
    q.offer(Node(1, 1, 1, 0))

    while (!q.isEmpty()) {
        val now = q.poll()

        if (now.x == M && now.y == N) {
            answer = now.distance
            break
        }

        for (d in 0..3) {
            val x = now.x + dx[d]
            val y = now.y + dy[d]

            if (y > N || y < 1 || x > M || x < 1) continue
            if (map[y][x] == 1) {
                if (now.broken < K && !visited[y][x][now.broken + 1]) {
                    visited[y][x][now.broken + 1] = true
                    q.offer(Node(x, y, now.distance + 1, now.broken + 1))
                }
            }else{
                if (!visited[y][x][now.broken]) {
                    visited[y][x][now.broken] = true
                    q.offer(Node(x, y, now.distance + 1, now.broken))
                }
            }
        }
    }

    if(answer == Int.MAX_VALUE)
        println(-1)
    else
        println(answer)


}

private data class Node(val x: Int, val y: Int, val distance: Int, val broken: Int)