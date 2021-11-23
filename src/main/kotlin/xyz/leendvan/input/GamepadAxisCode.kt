package xyz.leendvan.input

enum class GamepadAxisCode(val keycode:Int){
    Unknown(-1),
    Axis_Left_X(0),
    Axis_Left_Y(1),
    Axis_Right_X(2),
    Axis_Right_Y(3),
    Trigger_Left(4),
    Trigger_Right(5);

    companion object{

        @JvmStatic
        fun getKeyNameFromCode(code:Int):String{
            enumValues<GamepadAxisCode>().forEach {
                if(it.keycode == code){
                    return it.name
                }
            }
            return "Unknown"
        }

        @JvmStatic
        fun getKeycodeFromId(code:Int):GamepadAxisCode{
            enumValues<GamepadAxisCode>().forEach {
                if(it.keycode == code){
                    return it
                }
            }
            return Unknown
        }
    }
}