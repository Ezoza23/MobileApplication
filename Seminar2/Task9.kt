import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis
suspend fun download(ms: Long): String{
    delay(ms)
    return "file-$ms"
}
fun main() = runBlocking{
    val lstMs=listOf(100L, 200L, 300L, 400L, 500L)
    var result: List<String>
    val time1=measureTimeMillis {
        result=lstMs.map{n -> download(n)}
    }
    println(result)
    println("Time: $time1 ms")
//    val time2=measureTimeMillis {
//        result=coroutineScope{
//            lstMs.map{n ->
//            async{
//                download(n)
//            }
//            }.awaitAll()
//        }
//    }
//    println(result)
//    println("Time: $time2 ms")
//    val time3=measureTimeMillis {
//        result=coroutineScope{
//            lstMs.map{n->
//            async(start= CoroutineStart.LAZY){
//                download(n)
//            }}.awaitAll()
//        }
//    }
//    println(result)
//    println("Time: $time3 ms")
}