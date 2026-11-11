data class BookData(val title: String, val author: String, val pages: Int)

fun main() {
    val book1 = BookData("Kotlin in Action", "Dmitry Jemerov", 350)
    println("Book 1: $book1")
    val book2 = BookData("Kotlin in Action", "Dmitry Jemerov", 350)
    println("Are book1 and book2 equal? ${book1 == book2}")
    val book3 = book1.copy(pages = 400)
    println("Book 3 (copied with different pages): $book3")
}