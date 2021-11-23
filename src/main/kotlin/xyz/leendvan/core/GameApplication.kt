package xyz.leendvan.core

abstract class GameApplication {
    abstract fun init()

    abstract fun run()

    abstract fun update()

    abstract fun render()

    abstract fun shutdown()

    abstract fun dispose()
}