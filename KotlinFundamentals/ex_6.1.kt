fun notify(message: String, channel: String = "Email"): String{
    return "[$channel] $message"
}

fun main(){
    println(notify("World!"))
    println(notify("World!", "Hello"))
    println(notify(channel="Hello", message="World"))
}