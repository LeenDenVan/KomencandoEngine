package xyz.leendvan.audio

import org.lwjgl.openal.AL
import org.lwjgl.openal.AL10.AL_NO_ERROR
import org.lwjgl.openal.AL10.alGetError
import org.lwjgl.openal.ALC
import org.lwjgl.openal.ALC10.*
import org.lwjgl.openal.ALCCapabilities
import org.lwjgl.system.MemoryUtil.NULL
import java.nio.ByteBuffer
import java.nio.IntBuffer

object Audio {
    private var device:Long = 0L
    private var context:Long = 0L
    fun init(){
        val error = alGetError()
        if(error != AL_NO_ERROR)
            throw RuntimeException("Failed to initialize OpenAL 10")
        device = alcOpenDevice(null as ByteBuffer)
        if(device == NULL){
            throw IllegalStateException("Failed to open the default OpenAL device.")
        }
        var deviceCaps:ALCCapabilities = ALC.createCapabilities(device)
        this.context = alcCreateContext(device, null as IntBuffer)
        if(context == NULL){
            throw IllegalStateException("Failed to create OpenAL context.")
        }
        alcMakeContextCurrent(context)
        AL.createCapabilities(deviceCaps)
    }
}