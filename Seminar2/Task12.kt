import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val results = mutableListOf<Pair<String, Long>>()
    results += "runBlocking + delay(500)" to measureTimeMillis {
        runBlocking {
            repeat(4) { launch { delay(500) } }
        }
    }
    results += "runBlocking + Thread.sleep(500)" to measureTimeMillis {
        runBlocking {
            repeat(4) { launch { Thread.sleep(500) } }
        }
    }
    results += "Dispatchers.Default + Thread.sleep(500)" to measureTimeMillis {
        runBlocking {
            repeat(4) { launch(Dispatchers.Default) { Thread.sleep(500) } }
        }
    }
    val one = newSingleThreadContext("one")
    results += "newSingleThreadContext + Thread.sleep(500)" to measureTimeMillis {
        runBlocking {
            repeat(4) { launch(one) { Thread.sleep(500) } }
        }
    }
    one.close()

    println("availableProcessors: ${Runtime.getRuntime().availableProcessors()}")
    println()
    println("%-45s %6s".format("setup", "ms"))
    println("-".repeat(52))
    results.forEach { (name, ms) -> println("%-45s %6d".format(name, ms)) }
}