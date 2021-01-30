package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var FIRST_INDEX = Int.MIN_VALUE
private var sb = StringBuilder()

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var n = readLine().toInt()
    while (n-- > 0) {
        val number = readLine().toInt()
        bfs(number)
        if (sb.length > 100 || sb.isEmpty()) {
            println("BRAK")
        } else {
            println(sb.toString())
        }
    }
}

fun bfs(pairNumber: Int) {
    val queue = LinkedList<Int>()
    val isVisited = BooleanArray(20001) { false }
    val pair = Array<Pair<Int, Char>?>(20001) { null }

    sb = StringBuilder()

    queue.add(1)
    isVisited[1] = true
    pair[1] = Pair(FIRST_INDEX, '1')

    while (!queue.isEmpty()) {
        val beforeNumber = queue.pop()
        val first = (beforeNumber * 10).rem(pairNumber)
        val second = ((beforeNumber * 10) + 1).rem(pairNumber)

        if (!isVisited[first]) {
            pair[first] = Pair(beforeNumber, '0')
            if (first == 0)
                break
            isVisited[first] = true
            queue.add(first)
        }

        if (!isVisited[second]) {
            pair[second] = Pair(beforeNumber, '1')
            if (second == 0)
                break
            isVisited[second] = true
            queue.add(second)
        }
    }

    search(pair, 0)

}

private fun search(pair: Array<Pair<Int, Char>?>, number: Int) {
    if (number == FIRST_INDEX)
        return

    pair[number]?.first?.let {
       // print("$it ")
        search(pair, it)
    }

    pair[number]?.second?.let {
        sb.append(it)
    }
}


/*
1  % 9  = 1
10 % 9  = 1
11 % 9 =  2
100 % 9 = 1
101 % 9 = 2
110 % 9 = 2
111 % 9 = 3
*/

