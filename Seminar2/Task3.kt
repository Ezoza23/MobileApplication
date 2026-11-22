import kotlinx.coroutines.*
fun CoroutineScope.launchTicker(
    name: String,
    period: Long,
    times: Int
): Job{
    return launch{
        repeat(times){
            number->
            delay(period)
            println("$name ${number+1}")
        }
    }
}

suspend fun main()=coroutineScope{
    launchTicker("fast", 100, 3)
    launchTicker("slow", 250, 2)
    println("Tickers launched")
}