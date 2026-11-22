import kotlinx.coroutines.*
import java.util.concurrent.CancellationException

fun main() = runBlocking {

    val parent = launch {

        for (k in 1..3) {
            launch {
                try {
                    delay(k * 100L)
                    println("child $k done")

                } catch (e: CancellationException) {
                    println("child $k cancelled")
                    throw e
                }
            }
        }

        println("parent body done")
    }

    parent.join()

    println("parent completed")
}