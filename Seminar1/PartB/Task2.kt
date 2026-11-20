import kotlinx.coroutines.*
var timesCalled=0
suspend fun fetchName(): String?{
    delay(100)
    timesCalled++
    if (timesCalled<=2){
        return null
    }

    return "Ada"
}
suspend fun showUser(n: Int =3){
    for (i in 1..n){
        val name=fetchName()
        if (name != null) {
            println("User: $name")
            return
        }
        println("Try failed")
    }
    println("Server is down")
}

fun main()=runBlocking{
    showUser(3)
}