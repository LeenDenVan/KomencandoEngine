package xyz.leendvan.graphics

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWImage
import org.lwjgl.stb.STBImage.stbi_image_free
import org.lwjgl.stb.STBImage.stbi_load_from_memory
import org.lwjgl.system.MemoryUtil.memAllocInt
import xyz.leendvan.ResourceLoader

class Cursor {

    private var cursor:Long = 0L

    fun createCursor(path:String, xHotSpot:Int, yHotSpot: Int){
        cursor = glfwCreateCursor(ResourceLoader.loadGLFWImage(path, 4096), xHotSpot, yHotSpot)
    }

    fun createStandardCursor() {
        cursor = glfwCreateStandardCursor(GLFW_HRESIZE_CURSOR)
    }

    fun destruct() = glfwDestroyCursor(cursor)

    fun getCursor():Long = cursor

}