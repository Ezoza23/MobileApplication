fun main(){
    val playlist=listOf("To guide you home", "El-Andalus", "We love Life", "Wherever you are", "Without You")
    val favourite=mutableListOf<String>()
    for (i in 0..playlist.size-1 step 2){
        favourite.add(playlist[i])
    }
    println(favourite)
}