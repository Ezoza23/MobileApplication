import kotlinx.coroutines.*
fun main()=runBlocking{
    val job=launch(Dispatchers.Default){
        repeat(5){
            Thread.sleep(100)
            println("Work: $it")
        }
    }
    delay(250)
    job.cancelAndJoin()
}