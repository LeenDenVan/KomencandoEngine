package xyz.leendvan.input

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWKeyCallback
import org.lwjgl.glfw.GLFWMouseButtonCallback
import xyz.leendvan.graphics.Window

class KeyboardMouseInput : Input<Keycode> {
    private var pushedKey:ArrayList<Keycode> = ArrayList(255)
    private var releaseKey:ArrayList<Keycode> = ArrayList(255)
    private var holdingKey:ArrayList<Keycode> = ArrayList(255)
    private var pushedButton:ArrayList<Keycode> = ArrayList(32)
    private var releasedButton:ArrayList<Keycode> = ArrayList(32)
    private var holdingButton:ArrayList<Keycode> = ArrayList(32)
    override fun init() {
        Window.setKeyCallback(object : GLFWKeyCallback(){
            override fun invoke(window: Long, key: Int, scancode: Int, action: Int, mods: Int) {
                val keycode = Keycode.getKeycodeFromId(key)
                if(action == GLFW_PRESS){
                    pushedKey.add(keycode)
                    releaseKey.remove(keycode)
                    holdingKey.add(keycode)
                }else if(action == GLFW_RELEASE){
                    holdingKey.remove(keycode)
                    pushedKey.remove(keycode)
                }
            }
        })
        Window.setMouseButtonCallback(object : GLFWMouseButtonCallback(){
            override fun invoke(window: Long, button: Int, action: Int, mods: Int) {
                val keycode = Keycode.getKeycodeFromId(button)
                if(action == GLFW_PRESS){
                    pushedButton.add(keycode)
                    holdingButton.add(keycode)
                    releasedButton.remove(keycode)
                }else if(action == GLFW_RELEASE){
                    pushedButton.remove(keycode)
                    releasedButton.add(keycode)
                    holdingButton.remove(keycode)
                }
            }
        })
    }

    override fun updateInput() {
        pushedButton.forEach{
            println("Mouse ${it.name} is clicked")
        }
        pushedKey.forEach{
            println("${it.name} is clicked")
        }
        pushedButton.clear()
        pushedKey.clear()
        releasedButton.clear()
        releaseKey.clear()
    }

    override fun shutdown() {
        pushedButton.clear()
        pushedKey.clear()
        releaseKey.clear()
        releasedButton.clear()
        holdingButton.clear()
        holdingKey.clear()
    }

    override fun isKeyPushed(keyCode: Keycode): Boolean = pushedKey.contains(keyCode)

    override fun isKeyReleased(keyCode: Keycode): Boolean = releaseKey.contains(keyCode)

    override fun isKeyHolding(keyCode: Keycode): Boolean = holdingKey.contains(keyCode)

    fun isMouseBottonPushed(keyCode: Keycode):Boolean = pushedButton.contains(keyCode)

    fun isMouseBottonReleased(keyCode: Keycode):Boolean = releasedButton.contains(keyCode)

    fun isMouseBottonHolding(keyCode: Keycode):Boolean = holdingButton.contains(keyCode)

}