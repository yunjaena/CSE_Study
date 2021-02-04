package boj

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var num = readLine().toInt()
    var answer = 0
    if (num < 100)
        answer = num
    else {
        answer = 99
        while (num != 99) {
            if (num / 100 - num % 100 / 10 == num % 100 / 10 - num % 10)
                answer++
            num--
        }
    }
    println(answer)
}