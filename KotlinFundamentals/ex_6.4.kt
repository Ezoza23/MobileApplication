fun digitSum(n: Int): Int{
    var sumDigit=0
    var x=n
    while(x>0){
        sumDigit+=x%10
        x/=10
    }
    return sumDigit
}
fun main(){
    println(digitSum(1234))
    println(digitSum(507))
}