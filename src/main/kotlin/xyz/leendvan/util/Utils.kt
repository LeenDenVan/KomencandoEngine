package xyz.leendvan.util

import org.lwjgl.system.MemoryUtil.*

object Utils {
    fun getStringArrayFromMemory(pointer:Long, length:Int):Array<String>{
        var buffer = memPointerBuffer(pointer, length)
        return Array<String>(length){
            memUTF8(memByteBufferNT1(buffer[it]))
        }
    }
}