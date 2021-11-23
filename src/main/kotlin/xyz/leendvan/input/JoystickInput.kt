package xyz.leendvan.input

import org.lwjgl.glfw.GLFWJoystickCallback
import xyz.leendvan.graphics.Window

class JoystickInput : Input<GamepadKeycode>{

    private var pushedKey:ArrayList<GamepadKeycode> = ArrayList(255)
    private var releaseKey:ArrayList<GamepadKeycode> = ArrayList(255)
    private var holdingKey:ArrayList<GamepadKeycode> = ArrayList(255)

    override fun init() {
        Window.setJoystickCallback(object : GLFWJoystickCallback(){
            override fun invoke(jid: Int, event: Int) {

            }
        })
    }

    override fun updateInput() {

    }

    override fun shutdown() {

    }

    override fun isKeyPushed(keyCode: GamepadKeycode): Boolean = pushedKey.contains(keyCode)

    override fun isKeyReleased(keyCode: GamepadKeycode): Boolean = releaseKey.contains(keyCode)

    override fun isKeyHolding(keyCode: GamepadKeycode): Boolean = holdingKey.contains(keyCode)
}