package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

private lateinit var map: Array<BooleanArray>
private lateinit var isVisited: Array<BooleanArray>
private var n: Int = 0
private var m: Int = 0
private var case: Int = 0
private var min: Int = Int.MAX_VALUE
private var mapTotalMove = 0

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var str = readLine()
    while (true) {
        // init
        case++
        min = Int.MAX_VALUE
        mapTotalMove = 0
        val st = StringTokenizer(str)
        n = st.nextToken().toInt()
        m = st.nextToken().toInt()

        map = Array(n) {
            val line = readLine()
            BooleanArray(m) { index ->
                when (line[index]) {
                    '*' -> false
                    else -> {
                        mapTotalMove++
                        true
                    }
                }
            }
        }
        isVisited = Array(n) { BooleanArray(m) { false } }

        solve()

        println("Case $case: ${if (min == Int.MAX_VALUE) -1 else min}")
        str = readLine()
        if (str == null) return
    }
}

private fun solve() {
    (map.indices).forEach { y ->
        (map[0].indices).forEach { x ->
            if (map[y][x]) {
                isVisited[y][x] = true
                dfs(MapMoveInfo(x, y, 0, 1, null))
                isVisited[y][x] = false
            }
        }
    }
}

private fun dfs(moveInfo: MapMoveInfo) {
    if (moveInfo.move == mapTotalMove) {
        min = moveInfo.count.coerceAtMost(min)
        return
    }

    for (direction in Direction.values()) {
        val moveLength = isVisited.getMovableLength(moveInfo.x, moveInfo.y, direction, map)
        if ((moveLength.first == 0 && moveLength.second == 0) || direction == moveInfo.lastMoveInfo) continue

        val dx = moveInfo.x + moveLength.first
        val dy = moveInfo.y + moveLength.second
        val nextCount = moveInfo.count + 1
        val totalMove = moveInfo.move + abs(moveLength.first) + abs(moveLength.second)

        isVisited.set(moveInfo.x, moveInfo.y, dx, dy, true)
        dfs(MapMoveInfo(dx, dy, nextCount, totalMove, direction))
        isVisited.set(moveInfo.x, moveInfo.y, dx, dy, false)

    }

}

private fun Array<BooleanArray>.getMovableLength(x: Int, y: Int, direction: Direction, currentMap: Array<BooleanArray>): Pair<Int, Int> {
    var dx = x
    var dy = y

    while (canMove(dx + direction.x, dy + direction.y, currentMap)) {
        dx += direction.x
        dy += direction.y
    }

    return Pair(dx - x, dy - y)
}

private fun Array<BooleanArray>.set(startX: Int, startY: Int, endX: Int, endY: Int, isVisited: Boolean) {
    for (y in min(startY, endY)..max(startY, endY)) {
        for (x in min(startX, endX)..max(startX, endX)) {
            this[y][x] = isVisited
        }
    }
}

private fun Array<BooleanArray>.canMove(x: Int, y: Int, currentMap: Array<BooleanArray>): Boolean = !(x < 0 || y < 0 || y >= this.size || x >= this[0].size || this[y][x] || !currentMap[y][x])

private fun Array<BooleanArray>.print() = (this.indices).forEach { y ->
    (this[0].indices).forEach { x ->
        print(when (this[y][x]) {
            true -> '*'
            false -> '.'
        })
    }
    println()
}

class MapMoveInfo(val x: Int, val y: Int, val count: Int, val move: Int, val lastMoveInfo: Direction?)

enum class Direction(val x: Int, val y: Int) {
    UP(0, 1), RIGHT(1, 0), DOWN(0, -1), LEFT(-1, 0);
}