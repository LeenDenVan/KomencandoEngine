package xyz.leendvan

import java.io.File

object NativeEngine {
    init {
        System.loadLibrary("SDL2")
        System.loadLibrary("EngineLink")
        println("Libraries loaded")
    }

    public external fun initSDL2()

    public external fun rumbleIt(strength:Float, length:Int):Boolean

    public external fun genHapticEffect()

    public external fun getHaptic():Long
}