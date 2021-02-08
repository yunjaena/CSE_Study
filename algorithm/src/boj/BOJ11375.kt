package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

private val v = Array<ArrayList<Int>>(1010){ ArrayList() }
private var work = IntArray(1010){0}
private var isFinish = BooleanArray(1010){false}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    var count = 0


    for(i in 1 .. n){
        st = StringTokenizer(readLine())
        val p = st.nextToken().toInt()
        for(j in 0 until p){
            v[i].add(st.nextToken().toInt())
        }
    }

    for(i in 1 .. n){
        isFinish = BooleanArray(1010){false}
        if(dfs(i))
            count++
    }

    println(count)
}


private fun dfs(x : Int) : Boolean{
    for(i in 0 until v[x].size){
        val current = v[x][i]

        if(isFinish[current]){
            continue
        }
        isFinish[current] = true

        if(work[current] == 0 || dfs(work[current])){
            work[current] = x
            return true
        }
    }

    return false
}