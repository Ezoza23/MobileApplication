//import kotlinx.coroutines.*
//
//fun main() = runBlocking {
//    try {
//        coroutineScope {
//            launch { // A
//                try {
//                    delay(1000)
//                    println("A done")
//                } catch (e: CancellationException) {
//                    println("A cancelled")
//                    throw e
//                }
//            }
//
//            launch { // B
//                try {
//                    delay(300)
//                    println("B done")
//                } catch (e: CancellationException) {
//                    println("B cancelled")
//                    throw e
//                }
//            }
//
//            launch { // C
//                delay(200)
//                throw IllegalStateException("boom")
//            }
//        }
//    } catch (e: IllegalStateException) {
//        println("Caught: ${e.message}")
//    }
//}