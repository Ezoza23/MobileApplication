fun String.shout():String{
    return this.uppercase()+"!!!"
}
fun main(){
    println("hello".shout())
    println("kotlin is fun".shout())
}