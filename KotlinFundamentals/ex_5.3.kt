fun main(){
    val attempts=listOf(1111, 4321, 9999)
    val correct_pin=4321
    var index=0
    var isUnlocked=false
    while (index<attempts.size){
        if(attempts[index] == correct_pin){
            isUnlocked=true
            println("Entered correct pin: ${attempts[index]}")
            break
        }
        else{
            println("Wrong pin entered: ${attempts[index]}. Try again.")
        }
        index++
    }
    if (isUnlocked){
        println("Card is unlocked!")
    }
    else{
        println("Card is locked! ")
    }
}