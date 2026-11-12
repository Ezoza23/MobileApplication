object AppConfig{
    val appName="Some Name"
    var isDebug=false
    fun toggleDebug(){
        isDebug=!isDebug
    }
}
fun main(){
    println(AppConfig.isDebug)
    AppConfig.toggleDebug()
    println(AppConfig.isDebug)
    AppConfig.toggleDebug()
    println(AppConfig.isDebug)
    println(AppConfig.appName)
}