package xyz.leendvan.audio

import org.lwjgl.openal.AL10.*

class SoundSource(loop:Boolean, relative:Boolean) {
    private val sourceId:Int = alGenSources()
    init{
        if(loop) alSourcei(sourceId, AL_LOOPING, AL_TRUE)
        if(relative) alSourcei(sourceId, AL_SOURCE_RELATIVE, AL_TRUE)
    }

    fun setBuffer(buffer: SoundBuffer){
        stop()
        alSourcei(sourceId, AL_BUFFER, buffer.getId())
    }

    fun setGain(gain:Float) = alSourcef(sourceId, AL_GAIN, gain)

    fun setProperty(param:Int, value:Float) = alSourcef(sourceId, param, value)

    fun play() = alSourcePlay(sourceId)

    fun isPlaying() = alGetSourcei(sourceId, AL_SOURCE_STATE) == AL_PLAYING

    fun pause(){
        alSourcePause(sourceId)
    }

    fun stop() = alSourceStop(sourceId)

    fun cleanup(){
        stop()
        alDeleteBuffers(sourceId)
    }
}