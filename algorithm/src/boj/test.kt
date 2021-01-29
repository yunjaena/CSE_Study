package boj

inline fun inlined(block: () -> Unit) {
    println("hi!")
    block()
}
fun foo() {
    inlined {
        return // OK: the lambda is inlined
    }
    println("hello")
}
fun main() {
    foo()
}