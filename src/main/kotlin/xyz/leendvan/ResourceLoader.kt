package xyz.leendvan

import org.apache.commons.io.IOUtils
import org.lwjgl.BufferUtils
import org.lwjgl.BufferUtils.createIntBuffer
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWImage
import org.lwjgl.openal.AL10.*
import org.lwjgl.opengl.GL11.*
import org.lwjgl.stb.STBImage.*
import org.lwjgl.stb.STBVorbis.stb_vorbis_decode_memory
import org.lwjgl.system.MemoryStack
import org.lwjgl.system.MemoryStack.stackPush
import org.lwjgl.system.MemoryUtil
import org.lwjgl.system.MemoryUtil.*
import org.lwjgl.system.libc.LibCStdlib
import java.io.*
import java.nio.ByteBuffer
import java.nio.channels.Channels
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.Exception
import kotlin.RuntimeException

object ResourceLoader {

    private fun resizeBuffer(buffer:ByteBuffer, newCapacity:Int):ByteBuffer{
        var newBuffer = BufferUtils.createByteBuffer(newCapacity)
        buffer.flip()
        newBuffer.put(buffer)
        return newBuffer
    }

    fun ioResourceToByteBuffer(resource:String, bufferSize:Int):ByteBuffer{
        var buffer:ByteBuffer = BufferUtils.createByteBuffer(1)
        val path:Path = Paths.get(resource)
        if(Files.isReadable(path)){
            Files.newByteChannel(path).use {
                buffer = BufferUtils.createByteBuffer((it.size() + 1) as Int)
                while(it.read(buffer) != -1){}
            }
        }else{
            javaClass.getResourceAsStream(resource)?.let {
                Channels.newChannel(it)?.let { rbc ->
                    buffer = BufferUtils.createByteBuffer(bufferSize)
                    while(true){
                        val bytes = rbc.read(buffer)
                        if(bytes == -1) break
                        if( buffer.remaining() == 0)
                            buffer = resizeBuffer(buffer, buffer.capacity() * 3 / 2)
                    }
                }
            }
        }
        buffer.flip()
        return memSlice(buffer)
    }

    fun loadShader(path:String):String{
        return try {
            var shader: String = ""
            this.javaClass.getResourceAsStream(path)?.use { ins->
                var line: String?
                ins.bufferedReader().use {
                    while (true) {
                        line = it.readLine()
                        if(line == null) break
                        shader+=line+"\n"
                    }
                }
            }
            shader
        }catch (e:IOException){
            e.printStackTrace()
            ""
        }
    }

    fun loadGLFWImageBuffer(path:String, bufferSize: Int):GLFWImage.Buffer{
        return try {
            ioResourceToByteBuffer(path, bufferSize).let { pic ->
                var w = MemoryUtil.memAllocInt(1)
                var h = MemoryUtil.memAllocInt(1)
                var comp = MemoryUtil.memAllocInt(1)
                var iconn = GLFWImage.malloc(1)
                val pic = stbi_load_from_memory(pic, w, h, comp, 4)?.let {
                    iconn.position(0).width(w[0]).height(h[0]).pixels(it)
                    iconn.position(0)
                    stbi_image_free(it)
                }
                iconn
            }
        }catch (e:Exception){
            e.printStackTrace()
            throw RuntimeException("Failed to load image: $path")
        }
    }

    fun loadGLFWImage(path: String, bufferSize: Int):GLFWImage{
        return loadGLFWImageBuffer(path, bufferSize)[0]
    }

    public fun loadVorbis(file:String):Int{
        return try{
            var stack = stackPush()
            var channels = stack.mallocInt(1)
            var sampleRate = stack.mallocInt(1)

            var resourceContent = IOUtils.resourceToByteArray(file)
            var resourceBuffer = MemoryUtil.memAlloc(resourceContent.size)
            resourceBuffer.put(resourceContent)
            resourceBuffer.flip()

            var rawAudio = stb_vorbis_decode_memory(resourceBuffer, channels, sampleRate)

            MemoryUtil.memFree(resourceBuffer)

            var format = -1
            val numOfChannels = channels[0]
            if(numOfChannels == 1)
                format = AL_FORMAT_MONO16
            else if(numOfChannels == 2)
                format = AL_FORMAT_STEREO16

            var alBuffer = alGenBuffers()
            alBufferData(alBuffer, format, rawAudio, sampleRate[0])

            LibCStdlib.free(rawAudio)

            alBuffer
        }catch (e:IOException){
            e.printStackTrace()
            NULL as Int
        }
    }

    fun loadImage(path:String):IntArray{
        var imageBuffer:ByteBuffer
        try{
            imageBuffer = ioResourceToByteBuffer(path, 128 * 128)
        }catch (e:IOException){
            throw RuntimeException(e)
        }
        var stack = stackPush()
        var w = stack.mallocInt(1)
        var h = stack.mallocInt(1)
        var c = stack.mallocInt(1)

        if(!stbi_info_from_memory(imageBuffer, w, h, c)){
            throw RuntimeException("Failed to read image information: ${stbi_failure_reason()}")
        }else{
            println("OK with reason: ${stbi_failure_reason()}")
        }

        println("Image:width:${w[0]}, height:${h[0]}, comp:${c[0]}, HDR:${stbi_is_hdr_from_memory(imageBuffer)}")
        stbi_set_flip_vertically_on_load(true)
        var image = stbi_load_from_memory(imageBuffer, w, h, c, 0)
        image?.let{
            var width = w[0]
            var height = h[0]
            var comp = c[0]

            var texID = glGenTextures()

            glBindTexture(GL_TEXTURE_2D, texID)

            if(comp == 3){
                if((width and 3) != 0){
                    glPixelStorei(GL_UNPACK_ALIGNMENT, 2 - (width and 1))
                }
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, image)
            }else{
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, image)
            }

            stbi_image_free(image)
            memFree(w)
            memFree(h)
            memFree(c)
            return intArrayOf(texID, w.get(), h.get())
        }?:run{
            throw RuntimeException("Failed to load image: ${stbi_failure_reason()}")
        }
    }
}
