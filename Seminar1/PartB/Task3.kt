import kotlinx.coroutines.*
fun main()=runBlocking{
    val job=launch{
        try{
            for (i in 0..4){
                println("tick $i")
                delay(100)
            }}
        finally{
            println("cleanup")
        }
    }
    delay(250)
//    job.cancelAndJoin()
//    println("isCancelled=${job.isCancelled}")
    job.join()
}