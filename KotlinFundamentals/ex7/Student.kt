class Student(val name:String, val grades: List<Int>){
    fun averageGrade():Double{
        var sum=0.0
        for (grade in grades){
            sum+=grade
        }
        return sum/grades.size
    }
}
fun main() {
    val student1 = Student("Alice", listOf(85, 90, 92))
    val student2 = Student("Bob", listOf(75, 80, 88))
    println(student1.averageGrade())
    println(student2.averageGrade())
}
