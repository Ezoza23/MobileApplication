import kotlin.math.PI

interface Shape1 {
    fun area(): Double
    fun describe(): String {
        return "This shape has an area of ${area()}"
    }
}
class Circle1(val radius: Double) : Shape1 {
    override fun area(): Double {
        return PI * radius * radius
    }
}

class Rectangle1(val width: Double, val height: Double) : Shape1 {
    override fun area(): Double {
        return width * height
    }
}

fun main() {
    val circle = Circle1(5.0)
    val rectangle = Rectangle1(4.0, 6.0)

    println(circle.describe())
    println(rectangle.describe())
}

