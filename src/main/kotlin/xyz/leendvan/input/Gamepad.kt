package xyz.leendvan.input

data class Gamepad(val jid:Int, val GUID:String? = null, val name:String? = null){
    fun getAvailable() = !(GUID == null || name == null)
}