fun main() {
//    Ex 1.1
    val cityName="Tashkent"
//    cityName="London"
//    val can not be reassigned
    var temperature=23
    temperature=19
    println("$cityName: $temperature")

//    Ex 1.2
    val price=3000
    var stock=10
    var total=0

    stock-=1
    total+=price
    println("$stock; $total")

    stock-=1
    total+=price
    println("$stock; $total")

    stock-=1
    total+=price
    println("$stock; $total")

//    Ex 2.1
    val customer = "Alice"
    val total1 = 145000
    println("Thank you for your order, $customer! Total amount: $total1 sum.")
    println("Free delivery: ${total1 >= 100000}")
}