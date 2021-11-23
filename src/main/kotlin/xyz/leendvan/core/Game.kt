package xyz.leendvan.core

import xyz.leendvan.Configuration
import xyz.leendvan.NativeEngine
import xyz.leendvan.graphics.Window
import xyz.leendvan.input.GamepadInput
import xyz.leendvan.input.KeyboardMouseInput

abstract class Game{

    private var running:Boolean = true
    private lateinit var keyboardMouseInput:KeyboardMouseInput
    private lateinit var gamepadInput:GamepadInput
    private var configuration:Configuration = Configuration()

    open fun init() {
        settings(configuration)
        Window.init()
        Window.createWindow(configuration.width, configuration.height, configuration.title)
        keyboardMouseInput = KeyboardMouseInput()
        gamepadInput = GamepadInput()
        NativeEngine.initSDL2()
    }

    open fun run() {
        init()
        postInit()
        if(configuration.iconFile != ""){Window.setIcon(configuration.iconFile)}
        keyboardMouseInput.init()
        gamepadInput.init()
        while(!Window.shouldClose() && running){
            update()
        }
        dispose()
    }

    open fun postInit(){

    }

    open fun update() {
        render()
        Window.update()
        keyboardMouseInput.updateInput()
        gamepadInput.updateInput()
    }

    open fun render() {

    }

    open fun dispose() {
        Window.destroy()
    }

    fun shutdown() {
        running = false
    }

    open fun settings(configuration:Configuration){

    }

}