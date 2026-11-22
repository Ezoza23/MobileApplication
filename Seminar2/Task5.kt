import kotlinx.coroutines.*

fun main() = runBlocking {

    val job = launch(start = CoroutineStart.LAZY) {
        delay(500)
        println("body")
    }

    // 1. After creation
    println("1. active: ${job.isActive}, completed: ${job.isCompleted}, cancelled: ${job.isCancelled}")

    // 2. After starting
    job.start()
    delay(100)
    println("2. active: ${job.isActive}, completed: ${job.isCompleted}, cancelled: ${job.isCancelled}")

    // 3. After cancellation
    job.cancel()
    println("3. active: ${job.isActive}, completed: ${job.isCompleted}, cancelled: ${job.isCancelled}")

    // 4. After joining
    job.join()
    println("4. active: ${job.isActive}, completed: ${job.isCompleted}, cancelled: ${job.isCancelled}")
}