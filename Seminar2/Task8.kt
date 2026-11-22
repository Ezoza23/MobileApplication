import kotlinx.coroutines.*
fun main()=runBlocking{
    val job=launch{
        try{
            repeat(10){
                delay(100)
            }
        }
//        finally{
//            println("cleanup state")
//            delay(100)
//            println("draft saved")
//        }
        finally{
            withContext(NonCancellable) {
                println("cleanup start")
                delay(100)
                println("draft saved")
            }
        }
    }
    delay(250)
    job.cancelAndJoin()
}