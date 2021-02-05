package boj

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val letter = readLine()
    val reverse = letter.reversed()
    if(letter == reverse)
        println(1)
    else
        println(0)
}