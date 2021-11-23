package xyz.leendvan.graphics.gl.buffers

import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30.*
import xyz.leendvan.util.BufferUtil
import xyz.leendvan.util.Vector3f

class TriangleVBO : VBO {

    private var vbo:Int = glGenBuffers()
    private var vao:Int = glGenVertexArrays()
    private var size:Int = 0

    init{
        vbo = glGenBuffers()
        vao = glGenVertexArrays()
        println(vao)
        println(vbo)
        if(vao==0 || vbo == 0)println(glGetError())
    }

    fun allocate(vertices:Array<Vector3f>){
        size = vertices.size

        glBindVertexArray(vao)
        glBindBuffer(GL_ARRAY_BUFFER,vbo)
        glBufferData(GL_ARRAY_BUFFER, BufferUtil.createBuffer(vertices), GL_STATIC_DRAW)

        GL20.glVertexAttribPointer(0, 3, GL_FLOAT, false, Float.SIZE_BYTES * 3, 0)
        glEnableVertexAttribArray(0)

        glBindBuffer(GL_ARRAY_BUFFER, 0)

        glBindVertexArray(0)
    }

    fun allocate(vertices:FloatArray){

        glBindVertexArray(vao)
        glBindBuffer(GL_ARRAY_BUFFER,vbo)
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW)

        GL20.glVertexAttribPointer(0, 3, GL_FLOAT, false, Float.SIZE_BYTES * 3, 0)
        glEnableVertexAttribArray(0)

        glBindBuffer(GL_ARRAY_BUFFER, 0)

        glBindVertexArray(0)
    }

    override fun draw() {
        glBindVertexArray(vao)
        glDrawArrays(GL_TRIANGLES,0, 3)
    }

    override fun delete() {
        glDeleteVertexArrays(vao)
        GL15.glDeleteBuffers(vbo)
    }

}