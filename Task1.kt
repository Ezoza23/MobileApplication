import kotlinx.coroutines.*
suspend fun main(){
    coroutineScope{
        launch{
            delay(300)
            println("Coroutine A")
        }
        launch{
            delay(200)
            println("Coroutine B")
        }
        launch{
            delay(100)
            println("Coroutine C")
        }
        println("ALl launched")
    }
    println("Scope done")
}