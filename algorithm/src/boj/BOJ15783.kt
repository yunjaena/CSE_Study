package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var scc: Array<MutableList<Int>>
private val stack: Stack<Int> = Stack()
private lateinit var isVisited: BooleanArray

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    scc = Array(n + 1){ mutableListOf()}
    isVisited = BooleanArray(n + 1) { false }

    for(i in  0 until m){
        st = StringTokenizer(readLine())
        scc[st.nextToken().toInt()].add(st.nextToken().toInt())
    }
    for(i in 0 until n)
        dfs(i)

    isVisited = BooleanArray(n + 1) { false }

    var answer = 0

    while(!stack.isEmpty()){
        val node = stack.pop()
        if(isVisited[node]) continue
        answer++
        dfs(node, false)
    }

    println(answer)
}

private fun dfs(node: Int, pushAtStack: Boolean = true) {
    if (isVisited[node]) return
    isVisited[node] = true
    for (i in scc[node]) {
        dfs(i, pushAtStack)
    }
    if (pushAtStack) {
        stack.push(node)
    }
}

private fun Stack<Int>.print(){
    for(i in this){
        print("$i ")
    }
    println()
}