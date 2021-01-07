package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    val st = StringTokenizer(readLine())
    val sticks = IntArray(n) { st.nextToken().toInt() }
    val solution = Solution(sticks)
    println(solution.solve())
}

class Solution(private val sticks: IntArray) {
    private var min = Int.MAX_VALUE
    private val stickSize: Int = sticks.size
    private val selectStick: IntArray
    private val checkArray: BooleanArray = BooleanArray(sticks.size) { false }

    init {
        selectStick = IntArray(stickSize) { 0 }
    }

    fun solve(): Int {
        solve(0)
        return min
    }

    private fun solve(depth: Int) {
        if (depth == stickSize) {
            val cal = calc(this.selectStick)
            if (min > cal) {
                min = cal
            }
            return
        }

        for (stick in sticks.withIndex()) {
            if (!checkArray[stick.index]) {
                checkArray[stick.index] = true
                selectStick[depth] = stick.value
                solve(depth + 1)
                checkArray[stick.index] = false
            }
        }
    }

    private fun calc(array: IntArray): Int {
        var minValue = Int.MAX_VALUE

        for (selectSize in 1 until array.size) {
            var stickSum = sumArray(0, selectSize, array)
            if (stickSum > min) continue
            var sum = 0
            for (checkIndex in selectSize until array.size) {
                sum += array[checkIndex]
                if (sum > stickSum) {
                    break
                }
                if (checkIndex == array.size - 1 && sum == stickSum && minValue > stickSum) {
                    minValue = stickSum
                    break
                }
                if (sum == stickSum) {
                    sum = 0
                }
            }
        }
        return minValue
    }

    private fun sumArray(start: Int, end: Int, array: IntArray): Int {
        var sum = 0
        for (i in start until end) {
            sum += array[i]
        }
        return sum
    }

    private fun IntArray.print() {
        for (i in this) {
            print("$i ")
        }
        println()
    }
}

