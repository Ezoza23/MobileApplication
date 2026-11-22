import kotlinx.coroutines.*

fun main() = runBlocking {
    val a = launch(CoroutineName("A")) {
        repeat(3) {
            println("A $it")
            delay(300)
        }
    }

    val b = launch(CoroutineName("B")) {
        repeat(3) {
            println("B $it")
            delay(500)         
        }
    }
}