package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.floor

private var answer = Int.MAX_VALUE
private var rangeList: ArrayList<IntRange> = arrayListOf()
private var hashSet = HashSet<IntRange>()

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val s = readLine()
    divide(s, arrayListOf())
    if(answer != Int.MAX_VALUE) {
        println(answer)
        for (i in rangeList) {
            println("${i.first} ${i.last}")
        }
    }else{
        println("-1")
    }
}

fun divide(s: String, indices: ArrayList<IntRange>) {
    if (s.isEmpty()) {
        if (answer > indices.size) {
            answer = indices.size
            rangeList = indices
        }
        return
    }

    for (y in s.length - 1 downTo 1) {
        for (x in 0 until s.length - y) {
            val slice = s.slice(x..x + y)
            val a = slice.slice(0 until floor(slice.length / 2.0).toInt())
            val b = slice.slice(floor(slice.length / 2.0).toInt() until slice.length)
            if (a.isSame() && b.isSame() && a.length == b.length)
                divide(s.replaceRange(x..x + y, ""), indices.apply { add(x..x + y) })
        }
    }
}

private fun String.isSame(): Boolean {
    val start = this[0]
    for (i in this) {
        if (start != i)
            return false
    }
    return true
}

