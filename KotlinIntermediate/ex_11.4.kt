fun main(){
    val bookInstance1: Book? =Book("Devona", "Mehmet Olaqosh", 255)
    bookInstance1?.let { b ->
        println("Book: '${b.title}' by ${b.author}")
    }

    val bookInstance2=Book().apply{
        title="Garov"
        author="Anton Pavlovich Chexov"
        pages=10
    }
    println("Book: '${bookInstance2.title}' by ${bookInstance2.author} has pages of ${bookInstance2.pages}")

    val bookInstance3=Book().apply{
        title="Chuqur Uyqu"
        author="Rauf Jilasun"
        pages=260
    }.also{
        println("Created book '${it.title}'")
    }
}