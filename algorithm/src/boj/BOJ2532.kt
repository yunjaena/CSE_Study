package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.Comparator

/*
BOJ 2532 : 먹이사슬
https://www.acmicpc.net/problem/2532
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val N = readLine().toInt()
    val animalList = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until N) {
        val st = StringTokenizer(readLine())
        st.nextToken()
        animalList.add(Pair(st.nextToken().toInt(), st.nextToken().toInt()))
    }

    animalList.sortWith(kotlin.Comparator(::animalSort))
//    for (pair in animalList) {
//        println("${pair.first} : ${pair.second}")
//    }
    println(getAnimalGroupMaxSize(animalList, N))

}

fun getAnimalGroupMaxSize(animalList: MutableList<Pair<Int, Int>>, num: Int) : Int {
    val list = Array(num) { 0 }
    var len = 1
    list[0] = animalList[0].second

    for (i in 1 until num) {
        if (animalList[i].first == animalList[i - 1].first && animalList[i].second == animalList[i - 1].second) {
            continue
        }

        when {
            animalList[i].second >= list[0] -> {
                list[0] = animalList[i].second
            }
            animalList[i].second <= list[len - 1] -> {
                list[len++] = animalList[i].second
            }
            else -> {
                var index = Arrays.binarySearch(list, 0 , len,animalList[i].second){
                    c1 ,c2 -> c2.compareTo(c1)
                }
                index = if(index < 0) -index - 1 else index
                while(list[index] == animalList[i].second){
                    index++
                }
                list[index] = animalList[i].second;
            }
        }
//        for (n in list) {
//            print("$n ")
//        }
//        println()
    }
    return len;
}


fun animalSort(one: Pair<Int, Int>, two: Pair<Int, Int>) =
        if (one.first < two.first) {
            -1
        } else {
            if (one.first > two.first) {
                1
            } else {
                if (one.second > two.second) {
                    -1
                } else {
                    1
                }
            }
        }


