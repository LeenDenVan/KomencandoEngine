package xyz.leendvan.core

import xyz.leendvan.graphics.RendererEngine

class Engine {



    companion object{
        @JvmStatic
        private var fps:Int = 0
        @JvmStatic
        private var frameRate:Float = 200f
        @JvmStatic
        private var frameTime:Float = 1.0f / frameRate
        private var isRunning:Boolean = true
        private lateinit var rendererEngine:RendererEngine
    }
}