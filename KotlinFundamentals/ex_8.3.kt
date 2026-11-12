fun main(){
    val readings: List<String?> = listOf(null,null,"23.5","19.1",null)
    val firstValid = readings.firstOrNull { it != null } ?: "No data"
    println(firstValid)
}