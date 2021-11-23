package xyz.leendvan.util

import org.lwjgl.BufferUtils
import java.nio.DoubleBuffer
import java.nio.FloatBuffer

object BufferUtil {
    fun createBuffer(vector:Array<Vector2fd>):DoubleBuffer{
        BufferUtils.createDoubleBuffer(vector.size * Double.SIZE_BYTES * 2).let {
            vector.forEach { vec2 ->
                it.put(vec2[0])
                it.put(vec2[1])
            }
            it.flip()
            return it
        }
    }
    fun createBuffer(vector:Array<Vector3f>):FloatBuffer{
        BufferUtils.createFloatBuffer(vector.size * Double.SIZE_BYTES * 3).let {
            vector.forEach { vec2 ->
                it.put(vec2[0])
                it.put(vec2[1])
                it.put(vec2[2])
            }
            it.flip()
            return it
        }
    }
    fun createBuffer(vector:Array<Vector2f>): FloatBuffer {
        BufferUtils.createFloatBuffer(vector.size * Float.SIZE_BYTES * 2).let {
            vector.forEach { vec2 ->
                it.put(vec2[0])
                it.put(vec2[1])
            }
            it.flip()
            return it
        }
    }
}