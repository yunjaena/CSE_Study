package boj

import java.io.BufferedReader
import java.io.InputStreamReader

lateinit var coins: Array<BooleanArray>

fun main() {
    initCoins()
    coins.rowFlip(2)
}


fun initCoins() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().toInt()
    coins = Array(size) { BooleanArray(size) { false } }
    for (y in 0 until size) {
        val s = readLine()
        for (x in 0 until size) {
            when (s[x]) {
                'H' -> coins[y][x] = true
                'T' -> coins[y][x] = false
            }
        }
    }
}

private fun Array<BooleanArray>.print() = (this.indices).forEach { y ->
    (this[0].indices).forEach { x ->
        print(when (this[y][x]) {
            true -> 'H'
            false -> 'T'
        })
    }
    println()
}

fun Array<BooleanArray>.rowFlip(row: Int) =
        (this[0].indices).forEach { x ->
            this[row][x] = !this[row][x]
        }

fun Array<BooleanArray>.columnFlip(column: Int) =
        (this.indices).forEach { y ->
            this[y][column] = !this[column][y]
        }


fun simulation() {

}