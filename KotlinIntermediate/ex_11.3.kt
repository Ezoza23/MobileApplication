fun main(){
    val bookInstance=Book().apply{
        title="Devona"
        author="Mehmet Olaqosh"
        pages=255
    }.also{
        println("Created book ${it.title}")
    }
}