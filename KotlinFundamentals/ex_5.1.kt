fun grading(score: Int): String {
    val grade = when {
        score >= 90 -> "A"
        score >= 80 -> "B"
        score >= 70 -> "C"
        score >= 60 -> "D"
        else -> "F"
    }
    return grade
}

fun main(){
    println(grading(88))
}