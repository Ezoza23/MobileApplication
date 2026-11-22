import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

suspend fun loadName(): String{
    delay(100)
    return "E'zoza"
}
suspend fun loadAge(): Int{
    delay(100)
    return 20
}

fun main() = runBlocking {
    val time1 = measureTimeMillis {
        val name = loadName()
        val age = loadAge()
        println("Name: $name, Age: $age")
    }
    println("Sequential time: $time1 ms")
    val time2 = measureTimeMillis {
        val name = async { loadName() }
        val age = async { loadAge() }

        println("Name: ${name.await()}, Age: ${age.await()}")
    }
    println("Concurrent time: $time2 ms")
    val time3 = measureTimeMillis {
        val name = async { loadName() }.await()
        val age = async { loadAge() }.await()

        println("Name: $name, Age: $age")
    }
    println("Immediate await time: $time3 ms")
}
