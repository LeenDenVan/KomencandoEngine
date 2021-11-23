package xyz.leendvan.audio

import org.lwjgl.openal.AL10.*
import org.lwjgl.stb.STBVorbisInfo
import xyz.leendvan.ResourceLoader
import java.io.Closeable
import java.nio.ShortBuffer

class SoundBuffer(file:String) : Closeable{
    private val bufferId:Int =
        try{
            ResourceLoader.loadVorbis(file)
        }catch (e:Exception){
            e.printStackTrace()
            0
        }

    fun cleanup(){
        alDeleteBuffers(bufferId)
    }

    fun getId() = bufferId

    override fun close() {
        cleanup()
    }
}