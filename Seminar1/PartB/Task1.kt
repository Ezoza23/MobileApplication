import kotlinx.coroutines.*
fun main()=runBlocking {
    launch{
//        delay(200)
        Thread.sleep(200)
        println("A is done")
    }
    launch{
//        delay(100)
        Thread.sleep(100)
        println("B is done")
    }
    println("Start")
}
