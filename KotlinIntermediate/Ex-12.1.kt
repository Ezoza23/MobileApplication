fun buildGreeting(action: StringBuilder.() -> Unit): String {
    return StringBuilder().apply(action).toString()
}
fun main() {
    val greeting = buildGreeting {
        append("Hello, ")
        append("Kotlin ")
        append("developers!")
    }

    println(greeting)
}