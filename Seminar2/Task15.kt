import kotlinx.coroutines.*
import java.io.IOException
import kotlin.system.measureTimeMillis

data class Dashboard(val weather: String?, val news: String?, val rates: String?)

suspend fun loadDashboard(newsTimeout: Long = 450): Dashboard = coroutineScope {
    val weather = async(Dispatchers.IO) {
        withTimeoutOrNull(450) {
            try {
                delay(300)
                "Sunny"
            } finally {
                println("weather stopped")
            }
        }
    }

    val news = async(Dispatchers.IO) {
        withTimeoutOrNull(newsTimeout) {
            try {
                delay(600)
                "Headlines"
            } finally {
                println("news stopped")
            }
        }
    }

    val rates = async(Dispatchers.IO) {
        try {
            withTimeoutOrNull(450) {
                delay(100)
                throw IOException("rates failed")
            }
        } catch (e: IOException) {
            null
        }
    }

    Dashboard(weather.await(), news.await(), rates.await())
}

fun main() = runBlocking {
    val time = measureTimeMillis {
        println(loadDashboard())
    }
    println("time ~$time ms")

    val job = launch {
        try {
            println(loadDashboard())
        } catch (e: CancellationException) {
            println("Dashboard cancelled")
            throw e
        }
    }
    delay(200)
    job.cancelAndJoin()
}