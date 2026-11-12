class User(val name: String){
    companion object{
        fun create(name: String): User{
            return User(name)
        }
    }
}
fun main(){
    println((User.create("Amin")))
}