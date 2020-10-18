package boj

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val primNumberList = arrayListOf<Int>()
    val N = readLine().toInt()
    initPrime(primNumberList, N)
    println(count(primNumberList, N))
}

fun initPrime(primeNumberList: ArrayList<Int>, limit: Int) {
    if (limit < 2) {
        return
    }

    val isPrimeArray = Array(limit + 1) { it >= 2}

    var i = 2

    while (true) {
        if (i * i >  limit) {
            break
        }

        if (isPrimeArray[i]) {
            var j = i * i
            while (true) {
                if (j > limit) {
                    break
                }
                isPrimeArray[j] = false
                j += i
            }
        }
        i++
    }

    isPrimeArray.forEachIndexed { index, isPrime ->
        if (isPrime) {
            primeNumberList.add(index)
        }
    }
}

// TODO -> 투 포인터 적용하기
fun count(primeNumberList: ArrayList<Int>, limit: Int): Int {
    var count = 0
    var isEnd = false
    var index = 0
    if (primeNumberList.isEmpty())
        return 0
    while (!isEnd) {
        if (primeNumberList[0] > limit || index >= primeNumberList.size)
            isEnd = true
        var sum = 0
        for (i in index until primeNumberList.size) {
            sum += primeNumberList[i]
            if (sum >= limit) {
                if (sum == limit) count++
                break
            }
        }
        index++
    }
    return count
}