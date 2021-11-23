package xyz.leendvan.util

import org.lwjgl.glfw.GLFW.*

object Timer {
    @JvmStatic
    fun seconds() = glfwGetTime()

    @JvmStatic
    fun setTime(time:Double) = glfwSetTime(time)

    @JvmStatic
    fun getTimeValue() = glfwGetTimerValue()

    @JvmStatic
    fun getTimerFrequency() = glfwGetTimerFrequency()
}