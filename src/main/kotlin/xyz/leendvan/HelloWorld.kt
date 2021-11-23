package xyz.leendvan

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20.*
import org.lwjgl.opengl.GL30.glBindVertexArray
import org.lwjgl.opengl.GL30.glGenVertexArrays
import xyz.leendvan.core.Game
import xyz.leendvan.graphics.Texture
import xyz.leendvan.graphics.gl.buffers.TriangleVBO
import xyz.leendvan.graphics.gl.shader.Program
import xyz.leendvan.graphics.gl.shader.Shader
import xyz.leendvan.util.Timer
import xyz.leendvan.util.Vector3f
import xyz.leendvan.util.Vector4f
import kotlin.math.abs
import kotlin.math.sin

fun main(args:Array<String>){
    GameApp().run()
}

class GameApp : Game(){

    lateinit var vbo: TriangleVBO
    lateinit var shaProg: Program
    var vbo0:Int = 0
    var vao0:Int = 0
    var veo0:Int = 0
    lateinit var text:Texture
    lateinit var text2:Texture

    override fun settings(configuration: Configuration) {
        configuration.height = 600
        configuration.width = 800
        configuration.iconFile = "icon.jpg"
        configuration.title = "Hello Game Engine"
    }

    override fun init(){
        super.init()
        shaProg = Program(Shader("shaders/fragmentShader.shader", GL_FRAGMENT_SHADER),Shader("shaders/vertexShader.shader", GL_VERTEX_SHADER))
        shaProg.linkShaders()
        vao0 = glGenVertexArrays()
        vbo0 = glGenBuffers()
        veo0 = glGenBuffers()
        glBindVertexArray(vao0)

        glBindBuffer(GL_ARRAY_BUFFER, vbo0)
        GL15.glBufferData(GL_ARRAY_BUFFER, floatArrayOf(0.5f,  0.5f, 0.0f,   1.0f, 0.0f, 0.0f,   1.0f, 1.0f, // top right
            0.5f, -0.5f, 0.0f,   0.0f, 1.0f, 0.0f,   1.0f, 0.0f, // bottom right
            -0.5f, -0.5f, 0.0f,   0.0f, 0.0f, 1.0f,   0.0f, 0.0f, // bottom left
            -0.5f,  0.5f, 0.0f,   1.0f, 1.0f, 0.0f,   0.0f, 1.0f ), GL_STATIC_DRAW)

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, veo0)
        GL15.glBufferData(GL_ELEMENT_ARRAY_BUFFER, intArrayOf(0, 1, 3, 1, 2, 3), GL_STATIC_DRAW)

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 8 * Float.SIZE_BYTES, 0L)
        glEnableVertexAttribArray(0)

        glVertexAttribPointer(1, 3, GL_FLOAT, false, 8 * Float.SIZE_BYTES, 3 * Float.SIZE_BYTES.toLong())
        glEnableVertexAttribArray(1)

        glVertexAttribPointer(2, 2, GL_FLOAT, false, 8 * Float.SIZE_BYTES, 6 * Float.SIZE_BYTES.toLong())
        glEnableVertexAttribArray(2)

        text = Texture("img.png")
        text2 = Texture("img3.png")

        shaProg.use()
        shaProg.setInt("texture1", 0)
        shaProg.setInt("texture2",1)

        //vbo = TriangleVBO()
        //vbo.allocate(arrayOf(Vector3f(-0.5f, -0.5f, 0.0f), Vector3f(0.5f, -0.5f, 0.0f), Vector3f(0.0f, 0.5f, 0.0f)))
    }

    override fun update() {
        super.update()
    }

    override fun render() {
        super.render()
        glClearColor(0.2f, 0.3f, 0.3f, 1.0f)
        glClear(GL_COLOR_BUFFER_BIT)

        glActiveTexture(GL_TEXTURE0)
        text.bind()
        glActiveTexture(GL_TEXTURE1)
        text2.bind()
        shaProg.use()
        shaProg.setFloat("opac", abs(sin(Timer.seconds().toFloat())))
        glBindVertexArray(vao0)
        GL11.glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0)

        //vbo.draw()
    }

    override fun dispose() {
        super.dispose()

    }
}