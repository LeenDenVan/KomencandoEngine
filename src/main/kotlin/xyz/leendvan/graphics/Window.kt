package xyz.leendvan.graphics

import xyz.leendvan.ResourceLoader
import org.lwjgl.glfw.*
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL.createCapabilities
import org.lwjgl.opengl.GL11.glViewport
import org.lwjgl.opengl.GLCapabilities
import org.lwjgl.stb.STBImage.stbi_image_free
import org.lwjgl.stb.STBImage.stbi_load_from_memory
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil.NULL
import org.lwjgl.system.MemoryUtil.memAllocInt
import xyz.leendvan.util.Vector2d
import xyz.leendvan.util.Vector2fd

object Window {

    private var id:Long = -1
    private var vsync:Boolean = true
    private var resizable:Boolean = false
    private var width:Int = 0
    private var height:Int = 0

    fun init(){
        glfwSetErrorCallback(GLFWErrorCallback.createPrint())
        if(!glfwInit()){
            throw IllegalStateException("Unable to initialize GLFW")
        }
        checkAvailableGLVersion()
    }

    fun createWindow(width: Int, height: Int, title: String){
        createWindow(width, height, title, NULL, NULL)
    }

    fun createWindow(width: Int, height: Int, title: String, monitor: Long, share: Long){
        if(id != -1L) return
        id = glfwCreateWindow(width, height, title, monitor, share)
        if(id == NULL){
            glfwTerminate()
            throw RuntimeException("Failed to create the GLFW window")
        }
        val vidmode: GLFWVidMode? = glfwGetVideoMode(glfwGetPrimaryMonitor())
        glfwSetWindowPos(id,(vidmode!!.width() - width)/2, (vidmode.height() - height) / 2)
        glfwMakeContextCurrent(id)
        createCapabilities()
        setVsync(vsync)
        setFrameBufferSizeCallback(object : GLFWFramebufferSizeCallback() {
            override fun invoke(window: Long, width: Int, height: Int) {
                glViewport(0, 0, width, height)
            }
        })
    }

    fun setKeyCallback(keyCallback: GLFWKeyCallback) = glfwSetKeyCallback(id,keyCallback)

    fun setFrameBufferSizeCallback(frameBufferCallback: GLFWFramebufferSizeCallback) = glfwSetFramebufferSizeCallback(id, frameBufferCallback)

    fun setWindowPositonCallback(windowPosCallback: GLFWWindowPosCallback) = glfwSetWindowPosCallback(id,windowPosCallback)

    fun setWindowCloseCallback(windowCloseCallback: GLFWWindowCloseCallback) = glfwSetWindowCloseCallback(id, windowCloseCallback)

    fun setWindowRefreshCallback(windowRefreshCallback: GLFWWindowRefreshCallback) = glfwSetWindowRefreshCallback(id, windowRefreshCallback)

    fun setWindowFocusCallback(windowFocusCallback: GLFWWindowFocusCallback) = glfwSetWindowFocusCallback(id, windowFocusCallback)

    fun setWindowIconifyCallback(windowIconifyCallback: GLFWWindowIconifyCallback) = glfwSetWindowIconifyCallback(id,windowIconifyCallback)

    fun setWindowMaximizeCallback(windowMaximizeCallback: GLFWWindowMaximizeCallback) = glfwSetWindowMaximizeCallback(id,windowMaximizeCallback)

    fun setScrollCallback(scrollCallback: GLFWScrollCallback) = glfwSetScrollCallback(id, scrollCallback)

    fun setMouseButtonCallback(mouseButtonCallback: GLFWMouseButtonCallback) = glfwSetMouseButtonCallback(id, mouseButtonCallback)

    fun setCursorEnterCallback(cursorEnterCallback: GLFWCursorEnterCallback) = glfwSetCursorEnterCallback(id, cursorEnterCallback)

    fun setJoystickCallback(joystickCallback: GLFWJoystickCallback) = glfwSetJoystickCallback(joystickCallback)

    fun setDropCallback(dropCallback: GLFWDropCallback) = glfwSetDropCallback(id,dropCallback)

    fun isCursorHovered() = glfwGetWindowAttrib(id, GLFW_HOVERED)

    fun setCharCallback(charCallback:GLFWCharCallback) = glfwSetCharCallback(id, charCallback)

    fun setSize(width:Int, height:Int){
        this.width = width
        this.height = height
    }

    private fun checkAvailableGLVersion(){
        glfwDefaultWindowHints()
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
        val tmp:Long = glfwCreateWindow(1,1,"", NULL, NULL)
        glfwMakeContextCurrent(tmp)
        createCapabilities()
        var caps:GLCapabilities = createCapabilities()
        glfwDestroyWindow(tmp)

        glfwDefaultWindowHints()
        if(caps.OpenGL32){
            glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3)
            glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2)
            glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
            glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE)
        }else if(caps.OpenGL21){
            glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 2)
            glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1)
        }else{
            throw RuntimeException("Neither OpenGL 3.2 nor 2.1 is supported, your graphics driver may required to be updated.")
        }
    }

    fun shouldClose():Boolean = glfwWindowShouldClose(id)

    fun setTitle(title:CharSequence) = glfwSetWindowTitle(id, title)

    fun setVsync(isVsyn:Boolean){
        this.vsync = isVsyn
        glfwSwapInterval(if(vsync) 1 else 0)
    }

    fun setResizable(resizable:Boolean){
        this.resizable = resizable
        glfwWindowHint(GLFW_RESIZABLE, if(resizable) GLFW_TRUE else GLFW_FALSE)
    }

    fun update(){
        glfwSwapBuffers(id)
        glfwPollEvents()
    }

    fun destroy(){
        glfwDestroyWindow(id)
    }

    fun setCursor(cursor: Cursor) = glfwSetCursor(id, cursor.getCursor())

    fun setIcon(path:String){
        try{
            ResourceLoader.ioResourceToByteBuffer(path, 4096).let { icn ->
                var w = memAllocInt(1)
                var h = memAllocInt(1)
                var comp = memAllocInt(1)

                try{
                    var iconn = GLFWImage.malloc(1)
                    val p32 = stbi_load_from_memory(icn, w, h, comp, 4)?.let {
                        iconn.position(0).width(w[0]).height(h[0]).pixels(it)
                        iconn.position(0)
                        glfwSetWindowIcon(id, iconn)
                        stbi_image_free(it)
                    }
                }catch (e:Exception){
                    throw RuntimeException("Failed to load image from memory")
                }
            }
        }catch (e:Exception){
            throw java.lang.RuntimeException(e)
        }
    }

    fun getWindowPos(): Vector2d {
        return try{
            var stack = stackPush()
            var xBuffer = stack.mallocInt(1)
            var yBuffer = stack.mallocInt(1)
            glfwGetWindowPos(id, xBuffer, yBuffer)
            stack.pop()
            Vector2d(xBuffer.get(), yBuffer.get())
        }catch (e:Exception){
            e.printStackTrace()
            Vector2d(0,0)
        }
    }

    fun getCursorPos(): Vector2fd{
        return try{
            var stack = stackPush()
            var xBuf = stack.mallocDouble(1)
            var yBuf = stack.mallocDouble(1)
            glfwGetCursorPos(id, xBuf, yBuf)
            stack.close()
            Vector2fd(xBuf[0], yBuf[0])
        }catch (e:Exception){
            e.printStackTrace()
            Vector2fd(0.0, 0.0)
        }
    }

}
