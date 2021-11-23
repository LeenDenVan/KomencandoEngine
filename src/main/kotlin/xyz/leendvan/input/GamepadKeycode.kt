package xyz.leendvan.input

enum class GamepadKeycode(val keycode:Int){
    Unknown(-1),
    Button_A(0),
    Button_B(1),
    Button_X(2),
    Button_Y(3),
    Left_Bumper(4),
    Right_Bumper(5),
    Back(6),
    Start(7),
    Guide(8),
    Left_Thumb(9),
    Right_Thumb(10),
    DPad_Up(11),
    DPad_Right(12),
    DPad_Down(13),
    DPad_Left(14);
    companion object{
        fun getKeyNameFromCode(code:Int):String{
            enumValues<GamepadKeycode>().forEach {
                if(it.keycode == code){
                    return it.name
                }
            }
            return "Unknown"
        }

        fun getKeycodeFromId(code:Int):GamepadKeycode{
            enumValues<GamepadKeycode>().forEach {
                if(it.keycode == code){
                    return it
                }
            }
            return Unknown
        }
    }
}