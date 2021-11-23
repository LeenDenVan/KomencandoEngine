package xyz.leendvan.util

class Vector3f(private var x:Float, private var y:Float, private var z:Float) : Vector<Float>{
    override operator fun plus(vector2Fd: Vector<Float>) = Vector3f(x + vector2Fd[0], y + vector2Fd[1], z + vector2Fd[2])

    override operator fun minus(vector2Fd: Vector<Float>) = Vector3f(x - vector2Fd[0], y - vector2Fd[1], z + vector2Fd[2])

    override operator fun times(vector2Fd: Vector<Float>):Float = x * vector2Fd[0]+ y * vector2Fd[1] + z * vector2Fd[2]

    override operator fun times(num:Float) = Vector3f(x * num, y * num, z * num)

    override operator fun div(num:Float) = Vector3f(x / num, y / num, z * num)

    override operator fun dec() = Vector3f(x - 1, y - 1, z - 1)

    override operator fun inc() = Vector3f(x + 1, y + 1, z + 1)

    override operator fun unaryMinus() = Vector3f(-x, -y, -z)

    override operator fun not() = Vector3f(y, x, z)

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
            else -> {
                throw IllegalArgumentException("Invalid argument $axis, it should be x, y, z, X, Y, Z or 0, 1, 2")
            }
        }
    }
    override operator fun get(axis:Int):Float{
        return when(axis){
            0 -> { x }
            1 -> { y }
            2 -> { z }
            else -> {
                throw IllegalArgumentException("Invalid argument $axis, it should be x, y, z, X, Y, Z or 0, 1, 2")
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
           else -> {
               throw IllegalArgumentException("Invalid argument $axis, it should be x, y, z, X, Y, Z or 0, 1, 2")
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
            else -> {
                throw IllegalArgumentException("Invalid argument $axis, it should be x, y, X, Y or 0, 1")
            }
        }
    }

    override fun toString(): String {
        return "($x,$y,$z)"
    }

}