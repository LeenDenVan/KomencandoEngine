package xyz.leendvan.`object`

object ObjectPool : Iterable<GameObject>{
    private var objectPool:ArrayList<GameObject> = ArrayList()

    fun registryObject(obj:GameObject){
        objectPool.add(obj)
    }

    fun registryAllObject(vararg objs:GameObject){
        objectPool.addAll(objs)
    }

    fun removeObject(obj:GameObject){
        objectPool.remove(obj)
    }

    fun removeAllObject(vararg objs:GameObject){
        objectPool.removeAll(objs)
    }

    override fun iterator(): Iterator<GameObject> {
        return objectPool.iterator()
    }

}