package xyz.leendvan.util

interface Vector<T> {

    operator fun plus(vector2Fd: Vector<T>):Vector<T>

    operator fun minus(vector2Fd: Vector<T>):Vector<T>

    operator fun times(vector2Fd: Vector<T>):T

    operator fun times(num:T):Vector<T>

    operator fun div(num:T):Vector<T>

    operator fun dec():Vector<T>

    operator fun inc():Vector<T>

    operator fun unaryMinus():Vector<T>

    operator fun not():Vector<T>

    operator fun get(axis:String):T

    operator fun set(axis: String, value:T)

    operator fun get(axis:Int):T

    operator fun set(axis: Int, value:T)
}