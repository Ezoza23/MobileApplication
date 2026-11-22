import kotlinx.coroutines.*

fun log(step: String) = println("$step on ${Thread.currentThread().name}")

suspend fun loadAndShow() {
    log("start")

    val text = withContext(Dispatchers.IO) {
        delay(100)
        log("read file")
        "10,20,30"
    }

    val sum = withContext(Dispatchers.Default) {
        log("parse")
        text.split(",").sumOf { it.toInt() }
    }

    log("show $sum")
}

fun main() = runBlocking {
    loadAndShow()
}