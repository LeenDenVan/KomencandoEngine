package xyz.leendvan.input

interface Input<T> {

    fun init()

    fun updateInput()

    fun shutdown()

    fun isKeyPushed(keyCode:T):Boolean

    fun isKeyReleased(keyCode:T):Boolean

    fun isKeyHolding(keyCode: T):Boolean

}