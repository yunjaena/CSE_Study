package boj

import java.io.BufferedReader
import java.io.InputStreamReader

private lateinit var yahtzeePlayArray: Array<Yahtzee>
private lateinit var dp: Array<Int>

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().toInt()
    for (i in 1..N) {
        yahtzeePlayArray = Array(13) {
            val diceNumber = readLine().split(" ")
            val dice = Array(5) { index: Int -> diceNumber[index].toInt() }
            Yahtzee(dice)
        }

        //printYahtzeePlayArray()
        dp = Array(1.shl(13)) { -1 }
        dp[0] = 0
        println("Case $i: ${getMaxScore(1.shl(13) - 1, 0)}")
    }
}

fun getMaxScore(index: Int, step: Int): Int {
    if (dp[index] != -1)
        return dp[index]

    var result = 0

    for (i in 0 until 13) {
        val shift = 1.shl(i)
        if ((shift.and(index)) > 0) {
            result = result.coerceAtLeast(getMaxScore(index - shift, step + 1) + yahtzeePlayArray[i].score[12 - step])
            // println("category : ${12 - step} : ${Integer.toBinaryString(index)} : $result")
        }
    }

    if ((12 - step) == 5 && result >= 63)
        result += 35

    dp[index] = result
    return result
}

fun printYahtzeePlayArray() {
    for (y in yahtzeePlayArray) {
        for (num in y.score) {
            print("$num ")
        }
        println()
    }
}

class Yahtzee(private val dice: Array<Int>) {
    val score: Array<Int> = Array(13) { 0 }
    private val diceEyesCount: Array<Int> = Array(6) { index: Int -> count(index + 1) }
    private val straightCount: Int
        get() {
            if (dice.isNullOrEmpty()) return 0
            var count = 1
            var max = count
            for (i in 0 until dice.size - 1) {
                if (dice[i] + 1 == dice[i + 1]) {
                    count++
                } else if (dice[i] != dice[i + 1]) {
                    if (count > max)
                        max = count
                    count = 1
                }
            }
            if (count >= max)
                max = count
            return max
        }

    private val maxSameDice: Int
        get() {
            var max = 0
            for (i in diceEyesCount) {
                if (i >= max)
                    max = i
            }
            return max
        }

    init {
        initScore()
    }

    private fun initScore() {
        dice.sort()

        for (i in diceEyesCount.indices)
            score[i] = (i + 1) * diceEyesCount[i]

        if (isChance())
            score[6] = dice.sum()

        if (isThreeOfAKind())
            score[7] = dice.sum()

        if (isFourOfAKind())
            score[8] = dice.sum()

        if (isShortStraight())
            score[9] = 25

        if (isLongStraight())
            score[10] = 35

        if (isFullHouse())
            score[11] = 40

        if (isYahtzee())
            score[12] = 50
    }

    private fun count(diceNumber: Int): Int {
        var count = 0
        for (i in dice) {
            if (i == diceNumber)
                count++
        }
        return count
    }

    private fun isChance(): Boolean {
        return true
    }

    private fun isThreeOfAKind(): Boolean {
        return maxSameDice >= 3
    }

    private fun isFourOfAKind(): Boolean {
        return maxSameDice >= 4
    }

    private fun isShortStraight(): Boolean {
        return straightCount >= 4
    }

    private fun isLongStraight(): Boolean {
        return straightCount >= 5
    }

    private fun isFullHouse(): Boolean {
        var hasThreeEquals = false
        var hasTwoEquals = false

        for (i in diceEyesCount) {
            if (i == 3)
                hasThreeEquals = true
            if (i == 2)
                hasTwoEquals = true

            if (maxSameDice == 5 || (hasThreeEquals && hasTwoEquals))
                return true
        }

        return false
    }

    private fun isYahtzee(): Boolean {
        return maxSameDice == 5
    }


}