package boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

private lateinit var domino: Array<ArrayList<Int>>
private var isVisited = BooleanArray(100002){false}
private var stack = Stack<Int>()

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var t = readLine().toInt()
    var ans = 0
    var st: StringTokenizer
    while (t-- > 0) {
        ans = 0
        st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()

        domino = Array(n + 1) { ArrayList<Int>() }

        for (i in 0 until m) {
            st = StringTokenizer(readLine())
            val q = st.nextToken().toInt()
            val w = st.nextToken().toInt()
            domino[q].add(w)
        }

        for(i in 1 .. n){
            if(isVisited[i]) continue
            dfs(i, true)
        }
        isVisited = BooleanArray(100002){false}

        while (!stack.isEmpty()){
            val top = stack.pop()
            if(isVisited[top]) continue
            ans++
            dfs(top, false)
        }
    }
    print(ans)
}

fun dfs(index : Int, isVisit : Boolean){
    if(isVisited[index]) return
    isVisited[index] = true
    for(i in domino[index]){
        dfs(i, isVisit)
    }
    if(isVisit)
        stack.push(index)

}