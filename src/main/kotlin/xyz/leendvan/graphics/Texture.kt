package xyz.leendvan.graphics

import de.matthiasmann.twl.utils.PNGDecoder
import org.lwjgl.opengl.ARBFramebufferObject.glGenerateMipmap
import org.lwjgl.opengl.GL11.*
import xyz.leendvan.ResourceLoader
import java.nio.ByteBuffer

class Texture(path:String) {
    private var id = 0
    private var width = 0
    private var height = 0
    init{
        ResourceLoader.loadImage(path).let {
            id = it[0]
            width = it[1]
            height = it[2]
        }
        //noFilter()
        bilinearFilter()
    }

    fun bind() = glBindTexture(GL_TEXTURE_2D, id)

    fun delete() = glDeleteTextures(id)

    fun unbind() = glBindTexture(GL_TEXTURE_2D, 0)

    fun noFilter() {
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST)
    }

    fun bilinearFilter(){
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
    }

    fun trilinearFilter(){
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        glGenerateMipmap(GL_TEXTURE_2D)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR)
    }

    fun getID() = id

    fun getWidth() = width

    fun getHeight() = height
}