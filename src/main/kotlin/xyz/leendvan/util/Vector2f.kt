package xyz.leendvan.util

class Vector2f(private var x:Float, private var y:Float) : Vector<Float>{
    override operator fun plus(vector2Fd: Vector<Float>) = Vector2f(x + vector2Fd[0], y + vector2Fd[1])

    override operator fun minus(vector2Fd: Vector<Float>) = Vector2f(x - vector2Fd[0], y - vector2Fd[1])

    override operator fun times(vector2Fd: Vector<Float>):Float = x * vector2Fd[0]+ y * vector2Fd[1]

    override operator fun times(num:Float) = Vector2f(x * num, y * num)

    override operator fun div(num:Float) = Vector2f(x / num, y / num)

    override operator fun dec() = Vector2f(x - 1, y - 1)

    override operator fun inc() = Vector2f(x + 1, y + 1)

    override operator fun unaryMinus() = Vector2f(-x, -y)

    override operator fun not() = Vector2f(y, x)

    override operator fun get(axis:String):Float{
        if(axis == "x" || axis == "X"){
            return x
        }else if(axis == "y" || axis == "Y"){
            return y
        }
        throw IllegalArgumentException("Invalid argument $axis, it should be x, y, X, Y or 0, 1")
    }
    override operator fun get(axis:Int):Float{
        if(axis == 0){
            return x
        }else if(axis == 1){
            return y
        }
        throw IllegalArgumentException("Invalid argument $axis, it should be x, y, X, Y or 0, 1")
    }
    override operator fun set(axis: String, value:Float){
        if(axis == "x" || axis == "X"){
            this.x = value
            return
        }else if(axis == "y" || axis == "Y"){
            this.y = value
            return
        }
        throw IllegalArgumentException("Invalid argument $axis, it should be x, y, X, Y or 0, 1")
    }
    override operator fun set(axis: Int, value:Float){
        if(axis == 0){
            this.x = value
            return
        }else if(axis == 1){
            this.y = value
            return
        }
        throw IllegalArgumentException("Invalid argument $axis, it should be x, y, X, Y or 0, 1")
    }

    override fun toString(): String {
        return "($x,$y)"
    }

}