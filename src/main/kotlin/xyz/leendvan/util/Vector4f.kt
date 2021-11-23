package xyz.leendvan.util

class Vector4f(private var x:Float, private var y:Float, private var z:Float, private var w:Float) : Vector<Float>{
    override operator fun plus(vector2Fd: Vector<Float>) = Vector4f(x + vector2Fd[0], y + vector2Fd[1], z + vector2Fd[2], w + vector2Fd[3])

    override operator fun minus(vector2Fd: Vector<Float>) = Vector4f(x - vector2Fd[0], y - vector2Fd[1], z + vector2Fd[2], w + vector2Fd[3])

    override operator fun times(vector2Fd: Vector<Float>):Float = x * vector2Fd[0]+ y * vector2Fd[1] + z * vector2Fd[2] + w * vector2Fd[3]

    override operator fun times(num:Float) = Vector4f(x * num, y * num, z * num, w * num)

    override operator fun div(num:Float) = Vector4f(x / num, y / num, z * num, w * num)

    override operator fun dec() = Vector4f(x - 1, y - 1, z - 1, w - 1)

    override operator fun inc() = Vector4f(x + 1, y + 1, z + 1, w - 1)

    override operator fun unaryMinus() = Vector4f(-x, -y, -z, -w)

    override operator fun not() = Vector4f(y, x, w, z)

    override operator fun get(axis:String):Float{
        return when(axis){
            "x","X"->{
                x
            }
            "y","Y"->{
                y
            }
            "z","Z"->{
                z
            }
            "w", "W"->{
                w
            }
            else -> {
                throw IllegalArgumentException("Invalid argument $axis, it should be x, y, z, w, X, Y, Z, W or 0, 1, 2, 3")
            }
        }
    }
    override operator fun get(axis:Int):Float{
        return when(axis){
            0 -> { x }
            1 -> { y }
            2 -> { z }
            3 -> { w }
            else -> {
                throw IllegalArgumentException("Invalid argument $axis, it should be x, y, z, w, X, Y, Z, W or 0, 1, 2, 3")
            }
        }
    }
    override operator fun set(axis: String, value:Float){
       when(axis) {
           "x", "X" -> {
               this.x = value
           }
           "y", "Y" -> {
               this.y = value
           }
           "z", "Z" -> {
               this.z = value
           }
           "w", "W" -> {
               this.w = value
           }
           else -> {
               throw IllegalArgumentException("Invalid argument $axis, it should be x, y, z, w, X, Y, Z, W or 0, 1, 2, 3")
           }
       }
    }
    override operator fun set(axis: Int, value:Float){
        when(axis){
            0 -> {
                this.x = value
            }
            1 -> {
                this.y = value
            }
            2 -> {
                this.z = value
            }
            3 -> {
                this.w = value
            }
            else -> {
                throw IllegalArgumentException("Invalid argument $axis, it should be x, y, z, w, X, Y, Z, W or 0, 1, 2, 3")
            }
        }
    }

    override fun toString(): String {
        return "($x,$y,$z,$w)"
    }

}