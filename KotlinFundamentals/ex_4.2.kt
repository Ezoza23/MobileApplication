fun main(){
    fun main() {
        val menu = mapOf(
            "Coffee" to 3,
            "Croissant" to 4,
            "Sandwich" to 7,
            "Tea" to 2
        )
        for ((item, price) in menu) {
            println("$item costs $price")
        }
        val totalPrice = menu.values.sum()
        println("Total menu value: $totalPrice")
        val cheapest = menu.minByOrNull { it.value }
        val mostExpensive = menu.maxByOrNull { it.value }
        println("Cheapest item: ${cheapest?.key} (${cheapest?.value})")
        println("Most expensive item: ${mostExpensive?.key} (${mostExpensive?.value})")
    }

}
