open class Vehicle(val make: String, val model: String){
    open fun honk(): String{
        return "Beep!"
    }
}
class Car(make: String, model: String, val numberOfDoors: Int): Vehicle(make, model){
    override fun honk():String{
        return "Honk honk!"
    }
}

fun main() {
    val genericVehicle = Vehicle("Generic", "Model-X")
    val myCar = Car("Toyota", "Camry", 4)
    println("${genericVehicle.make} ${genericVehicle.model} says: ${genericVehicle.honk()}")
    println("${myCar.make} ${myCar.model} with ${myCar.numberOfDoors} doors says: ${myCar.honk()}")
}