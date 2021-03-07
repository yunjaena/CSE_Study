package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val numbers = mutableListOf<String>()
    val answer = mutableListOf<String>()
    var st = StringTokenizer(readLine())
    val k = st.nextToken().toInt()
    val n = st.nextToken().toInt()

    for (i in 0 until k) {
        numbers.add(readLine())
    }

    numbers.sortWith { first, second ->
        second.toInt().compareTo(first.toInt())
    }

    for (i in k until n) {
        answer.add(numbers.first())
    }
    for (s in numbers) {
        answer.add(s)
    }

    answer.sortWith { first, second ->
        (second + first).compareTo(first + second)
    }

    for (s in answer) {
        print(s)
    }
}