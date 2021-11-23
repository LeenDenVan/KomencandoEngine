package xyz.leendvan.util

class Vector2fd(private var x:Double, private var y:Double) : Vector<Double>{
    override operator fun plus(vector2Fd: Vector<Double>) = Vector2fd(x + vector2Fd[0], y + vector2Fd[1])

    override operator fun minus(vector2Fd: Vector<Double>) = Vector2fd(x - vector2Fd[0], y - vector2Fd[1])

    override operator fun times(vector2Fd: Vector<Double>):Double = x * vector2Fd[0]+ y * vector2Fd[1]

    override operator fun times(num:Double) = Vector2fd(x * num, y * num)

    override operator fun div(num:Double) = Vector2fd(x / num, y / num)

    override operator fun dec() = Vector2fd(x - 1, y - 1)

    override operator fun inc() = Vector2fd(x + 1, y + 1)

    override operator fun unaryMinus() = Vector2fd(-x, -y)

    override operator fun not() = Vector2fd(y, x)

    override operator fun get(axis:String):Double{
        if(axis == "x" || axis == "X"){
            return x
        }else if(axis == "y" || axis == "Y"){
            return y
        }
        throw IllegalArgumentException("Invalid argument $axis, it should be x, y, X, Y or 0, 1")
    }
    override operator fun get(axis:Int):Double{
        if(axis == 0){
            return x
        }else if(axis == 1){
            return y
        }
        throw IllegalArgumentException("Invalid argument $axis, it should be x, y, X, Y or 0, 1")
    }
    override operator fun set(axis: String, value:Double){
        if(axis == "x" || axis == "X"){
            this.x = value
            return
        }else if(axis == "y" || axis == "Y"){
            this.y = value
            return
        }
        throw IllegalArgumentException("Invalid argument $axis, it should be x, y, X, Y or 0, 1")
    }
    override operator fun set(axis: Int, value:Double){
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