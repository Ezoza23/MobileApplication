import kotlinx.coroutines.*
suspend fun fetchPrice(): Int{
    delay(500)
    return 42
}
fun main() = runBlocking{
    val job=withTimeoutOrNull(300){
        fetchPrice()
    }
    println("price is $job")
    val job2=withTimeoutOrNull(800){
        fetchPrice()
    }
    println("price is $job2")
    try{
        val job3=withTimeout(300){
            fetchPrice()
        }
        println("price is $job3")
    }
    catch (e: TimeoutCancellationException){
        println("timeout caught")
    }
}