package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*


fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val s = st.nextToken().toInt()
    val arr = IntArray(n){readLine().toInt()}
    val sumArray = initSumArray(arr)
    for(startIndex in 0 until n){
        val start = startIndex + 1
        var length = ((n - startIndex) / 2) * 2

        when {
            length < 2 -> {
                println(0)
            }
            getSum(sumArray,start,length+startIndex) < s -> {
                println(length)
            }
            else -> {
                println(solve(start, length, s, sumArray))
            }
        }

    }
}

private fun solve(start : Int, length : Int , max : Int, sumArray: IntArray) : Int{
    var len: Int = length
    while(len >= 2) {
        val mid = start + (len / 2) - 1
        if (getSum(sumArray, start, mid) <= max && getSum(sumArray, mid + 1, start + len - 1) <= max)
            return len
        len -= 2
    }
    return 0
}

private fun initSumArray(array: IntArray) : IntArray{
    val sumArray = IntArray(array.size + 1){0}
    for(i in 1 .. array.size)
        sumArray[i] = sumArray[i-1] + array[i-1]
    return sumArray
}

private fun getSum(sumArray: IntArray, start : Int, end : Int) = sumArray[end] - sumArray[start-1]
