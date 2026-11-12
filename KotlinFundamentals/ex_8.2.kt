fun describeString(maybeString: String?): String{
    return maybeString?:"Empty or null"
}
fun main(){
    println(describeString(""))
    println(describeString("abc"))
    println(describeString(null))
}