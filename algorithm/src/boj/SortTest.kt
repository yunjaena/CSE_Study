package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val numbers = mutableListOf<String>()
    val numbers1 = mutableListOf<String>()
    val numbers2 = mutableListOf<String>()
    val answer = mutableListOf<String>()
    var st = StringTokenizer(readLine())
    val k = st.nextToken().toInt()

    for (i in 0 until k) {
        val rand = Random().nextInt(100).toString()
        numbers.add(rand)
        numbers1.add(rand)
        numbers2.add(rand)
    }

    numbers.sortWith { first, second ->
        (second + first).compareTo(first + second)
    }

    numbers.forEach {
        print("$it ")
    }

    println()
    println()
//
//    numbers.sortWith { first, second ->
//        second.compareTo(first)
//    }
//
//    numbers.forEach {
//        print("$it ")
//    }


    val comparator = Comparator<String> { o1, o2 ->
        ((o1.plus(o2)).toInt()).compareTo(o2.plus(o1).toInt())
    }


    Collections.sort(numbers1, comparator)

    numbers1.forEach {
        print("$it ")
    }

}