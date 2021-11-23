package xyz.leendvan.graphics.gl.shader

import org.lwjgl.opengl.GL20.*
import org.lwjgl.system.MemoryStack.stackPush
import xyz.leendvan.ResourceLoader
import xyz.leendvan.util.Vector3f
import xyz.leendvan.util.Vector4f

class Shader(shaderSource:String, shaderType:Int) {
    private var shader:String = ResourceLoader.loadShader(shaderSource)
    private val shaderID = glCreateShader(shaderType)
    init{
        glShaderSource(shaderID, shader)
        glCompileShader(shaderID)
        getError(shaderID)
    }
    fun getID() = shaderID

    fun cleanup(){
        glDeleteProgram(shaderID)
    }

    private fun getError(shaderID:Int){
        try{
            var stack = stackPush()
            var intBuff = stack.mallocInt(1)
            glGetShaderiv(shaderID, GL_COMPILE_STATUS, intBuff)
            if(intBuff[0] == 0){
                throw RuntimeException(glGetShaderInfoLog(shaderID))
            }else{
                println("Shader loaded successfully")
            }
            stack.close()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}