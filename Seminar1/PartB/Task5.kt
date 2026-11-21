
import kotlinx.coroutines.*

fun main() = runBlocking {
    launch(Dispatchers.Default) {
        println("Default: ${Thread.currentThread().name}")
    }

    launch(Dispatchers.IO) {
        println("IO: ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Unconfined) {
        println("Unconfined before delay: ${Thread.currentThread().name}")

        delay(100)

        println("Unconfined after delay: ${Thread.currentThread().name}")
    }

    val myThread = newSingleThreadContext("MyThread")

    launch(myThread) {
        println("MyThread before delay: ${Thread.currentThread().name}")

        delay(100)

        println("MyThread after delay: ${Thread.currentThread().name}")
    }

    launch {
        println("Inherited: ${Thread.currentThread().name}")
    }

    myThread.close()
}
