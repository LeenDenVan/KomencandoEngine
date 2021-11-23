package xyz.leendvan.graphics

import xyz.leendvan.`object`.GameObject
import xyz.leendvan.`object`.ObjectPool

class RendererEngine {

    fun render(){
        getObjectIterator().forEach {
            it.render()
        }
    }

    private fun getObjectIterator():Iterator<GameObject>{
        return ObjectPool.iterator()
    }

    companion object{

    }

}