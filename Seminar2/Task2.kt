import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    println("Before: ${Thread.currentThread().name}")

    launch(CoroutineName("worker-1")) {
        println("After: ${Thread.currentThread().name}")
    }
}