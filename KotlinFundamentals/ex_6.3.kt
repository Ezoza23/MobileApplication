fun isPassing(score: Int): Boolean{
    return score>=60
}

fun main(){
    val scores=listOf(45, 60, 82, 59, 91, 60, 33)
    val isPassed=scores.filter{isPassing(it)}
    println(isPassed)
}