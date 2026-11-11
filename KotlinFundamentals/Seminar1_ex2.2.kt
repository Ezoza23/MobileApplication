fun printReceipt(item: String, price: Int){
    println("""
        Item: "$item";
        Price: $$price;
        Thank you for your purchase!
    """.trimIndent())
}

fun main(){
    printReceipt("Cappuccino", 25)
    printReceipt("Latte", 20)
}