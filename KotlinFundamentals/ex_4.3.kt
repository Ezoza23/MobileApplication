fun main() {
    val signInSheet = listOf("Alice", "Bob", "Alice", "Charlie", "David", "Bob")
    val uniqueStudentsSet = signInSheet.toSet()
    val attendanceMap = uniqueStudentsSet.associateWith { true }
    println("Unique students present: ${attendanceMap.size}")
    attendanceMap.forEach { (student, isPresent) ->
        println("$student: Present = $isPresent")
    }
}