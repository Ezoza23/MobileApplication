fun main() {
    val myBook = Book().apply {
        title = "Xalqa"
        author = "Akrom Malik"
        pages = 320
    }

    println("Configured Book: '${myBook.title}' by ${myBook.author} (${myBook.pages} pages)")
}