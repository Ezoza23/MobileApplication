import kotlinx.coroutines.*

class FakeViewModel {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    fun load() {
        scope.launch {
            for (k in 1..3) {
                delay(200)
                println("step $k")
            }
        }
    }

    fun onCleared() {
        println("onCleared")
        scope.cancel() // remove this line to see the leak
    }
}

fun main() = runBlocking {
    val vm = FakeViewModel()
    vm.load()
    delay(300)
    vm.onCleared()
    delay(1000)
}