import kotlin.math.PI

abstract class Shape {
    abstract fun area(): Double
    fun describe(): String {
        return "This shape has an area of ${area()}"
    }
}

class Circle(val radius: Double) : Shape() {
    override fun area(): Double {
        return PI * radius * radius
    }
}

class Rectangle(val width: Double, val height: Double) : Shape() {
    override fun area(): Double {
        return width * height
    }
}

fun main() {
    val circle = Circle(5.0)
    val rectangle = Rectangle(4.0, 6.0)

    println(circle.describe())
    println(rectangle.describe())
}