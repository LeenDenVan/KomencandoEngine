package xyz.leendvan.graphics.gl.shader

import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL20.*
import org.lwjgl.system.MemoryStack
import xyz.leendvan.util.Vector3f
import xyz.leendvan.util.Vector4f

class Program(vararg shaders: Shader) {
    private val programID:Int = glCreateProgram()
    private var shaders: Array<out Shader> = shaders
    init {
        attachShaders(*shaders)
    }

    fun attachShader(shader:Shader) = glAttachShader(programID, shader.getID())

    fun attachShaders(vararg shaders: Shader) = shaders.forEach {
        attachShader(it)
    }

    fun linkShaders(){
        glLinkProgram(programID)
        getError()
        shaders.forEach {
            it.cleanup()
        }
    }

    private fun getError(){
        try{
            var stack = MemoryStack.stackPush()
            var intBuff = stack.mallocInt(1)
            glGetProgramiv(programID,GL_LINK_STATUS, intBuff)
            if(intBuff[0] == 0){
                throw RuntimeException(glGetProgramInfoLog(programID))
            }else{
                println("Program linked successfully")
            }
            stack.close()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun use() = glUseProgram(programID)

    fun getID() = programID

    fun delete() = glDeleteProgram(programID)

    fun setBool(name:String, value:Boolean){
        GL20.glUniform1i(GL20.glGetUniformLocation(programID, name), if (value) 1 else 0)
    }

    fun setInt(name:String, value:Int){
        GL20.glUniform1i(GL20.glGetUniformLocation(programID, name), value)
    }

    fun setFloat(name:String, value:Float){
        GL20.glUniform1f(GL20.glGetUniformLocation(programID, name), value)
    }

    fun setVec4(name:String, value: Vector4f){
        GL20.glUniform4f(GL20.glGetUniformLocation(programID, name), value[0], value[1], value[2], value[3])
    }

    fun setVec3(name:String, value: Vector3f){
        GL20.glUniform3f(GL20.glGetUniformLocation(programID, name), value[0], value[1], value[2])
    }
}