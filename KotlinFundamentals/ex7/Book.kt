package Seminar1.ex_7

class Book (val title: String, val author: String, val pages: Int)
fun main(){
    val instance1=Book("Tarixi Muhammadiy", "Alixonto'ra Sog'uniy", 500)
    println("Book tile: ${instance1.title}, author of the book: ${instance1.author}, pages: ${instance1.pages}")
    val instance2=Book("Devona", "Mehmet Olaqosh", 255)
    println("Book tile: ${instance2.title}, author of the book: ${instance2.author}, pages: ${instance2.pages}")
}
