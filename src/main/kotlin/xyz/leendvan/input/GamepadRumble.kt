package xyz.leendvan.input

import xyz.leendvan.NativeEngine

class GamepadRumble {
    init{
        System.loadLibrary("SDL2")
        System.loadLibrary("EngineLink")
        NativeEngine.initSDL2()
    }

}