package xyz.leendvan.input

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWJoystickCallback
import xyz.leendvan.core.Game
import xyz.leendvan.graphics.Window
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class GamepadInput : Input<GamepadKeycode>{

    private var availableGamepadList:Array<Gamepad> = Array(16){
        Gamepad(it, glfwGetJoystickGUID(it), glfwGetJoystickName(it))
    }

    private var gamepadPushedKey:HashMap<Int,ArrayList<GamepadKeycode>> = HashMap(16)
    private var gamepadReleasedKey:HashMap<Int,ArrayList<GamepadKeycode>> = HashMap(16)
    private var gamepadHoldingKey:HashMap<Int,ArrayList<GamepadKeycode>> = HashMap(16)
    private var gamepadAxis:HashMap<Int, HashMap<GamepadAxisCode, Float>> = HashMap(16)

    override fun init() {
        repeat(16){
            gamepadPushedKey[it] = ArrayList<GamepadKeycode>()
            gamepadReleasedKey[it] = ArrayList<GamepadKeycode>()
            gamepadHoldingKey[it] = ArrayList<GamepadKeycode>()
            gamepadAxis[it] = HashMap()
        }
        availableGamepadList.forEach { println(it) }
        Window.setJoystickCallback(object : GLFWJoystickCallback(){
            override fun invoke(jid: Int, event: Int) {
                if(event == GLFW_CONNECTED){
                    availableGamepadList[jid] = Gamepad(jid, glfwGetJoystickGUID(jid), glfwGetJoystickName(jid))
                    println("${glfwGetJoystickGUID(jid)} ${glfwGetJoystickName(jid)} is connected" )
                }else if(event == GLFW_DISCONNECTED){
                    println("${availableGamepadList[jid].GUID} ${availableGamepadList[jid].name} is disconnected")
                    availableGamepadList[jid] = Gamepad(jid, null, null)
                }
            }
        })
    }

    override fun updateInput() {
        clearMap(gamepadPushedKey)
        clearMap(gamepadReleasedKey)
        availableGamepadList.forEach {
            if(it.getAvailable()){
                updateButtonState(it)
                updateAxisState(it)
            }
        }
    }

    override fun shutdown() {
        gamepadAxis.clear()
        gamepadHoldingKey.clear()
        gamepadPushedKey.clear()
        gamepadReleasedKey.clear()
    }

    private fun updateButtonState(it:Gamepad){
        getButtonStates(it.jid).forEach{ code , value ->
            if(value){
                if(isKeyReleased(it.jid, code)){
                    gamepadReleasedKey[it.jid]?.remove(code)
                    gamepadHoldingKey[it.jid]?.add(code)
                    gamepadPushedKey[it.jid]?.add(code)
                }
            }else{
                gamepadReleasedKey[it.jid]?.add(code)
                if(isKeyPushed(it.jid, code)){
                    gamepadPushedKey[it.jid]?.remove(code)
                }
                if(isKeyHolding(it.jid, code)){
                    gamepadHoldingKey[it.jid]?.remove(code)
                }
            }
        }
    }

    private fun updateAxisState(it:Gamepad){
        gamepadAxis[it.jid] = getAxisStates(it.jid)
    }

    override fun isKeyPushed(keyCode: GamepadKeycode): Boolean{
        var res = false
        gamepadPushedKey.forEach { jid, value ->
            if(availableGamepadList[jid].getAvailable()){
                if(isKeyPushed(jid, keyCode)){
                    res = true
                    return@forEach
                }
            }
        }
        return res
    }

    private fun clearMap(hashMap: HashMap<Int, ArrayList<GamepadKeycode>>){
        hashMap.forEach{
            it.value.clear()
        }
    }

    fun isKeyPushed(jid:Int, keyCode:GamepadKeycode): Boolean = gamepadPushedKey[jid]?.contains(keyCode) ?: false

    override fun isKeyReleased(keyCode: GamepadKeycode): Boolean {
        var res = false
        gamepadReleasedKey.forEach { jid, value ->
            if(availableGamepadList[jid].getAvailable()){
                if(isKeyPushed(jid, keyCode)){
                    res = true
                    return@forEach
                }
            }

        }
        return res
    }

    fun isKeyReleased(jid:Int, keyCode:GamepadKeycode): Boolean = gamepadReleasedKey[jid]?.contains(keyCode) ?: false

    override fun isKeyHolding(keyCode: GamepadKeycode): Boolean {
        var res = false
        gamepadHoldingKey.forEach { jid, value ->
            if(availableGamepadList[jid].getAvailable()){
                if(isKeyHolding(jid, keyCode)){
                    res = true
                    return@forEach
                }
            }
        }
        return res
    }

    fun isKeyHolding(jid:Int, keyCode:GamepadKeycode): Boolean = gamepadHoldingKey[jid]?.contains(keyCode) ?: false

    fun getButtonStates(jid:Int):HashMap<GamepadKeycode, Boolean>{
        var gameState:HashMap<GamepadKeycode, Boolean> = HashMap()
        var i:Int = 0
        glfwGetJoystickButtons(jid)?.let{
            gameState[GamepadKeycode.getKeycodeFromId(i)] = it[i++].compareTo(1) == 0
        }
        return gameState
    }

    fun getAxisStates(jid:Int):HashMap<GamepadAxisCode, Float>{
        var axisState:HashMap<GamepadAxisCode, Float> = HashMap()
        var i:Int = 0
        glfwGetJoystickAxes(jid)?.let {
            axisState[GamepadAxisCode.getKeycodeFromId(i)] = it[i++]
        }
        return axisState
    }

}