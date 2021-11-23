package xyz.leendvan.event

abstract class Event {

    private lateinit var message:Message
    private lateinit var eventHandler: EventHandler<Event>

    fun setMessage(message: Message){
        this.message = message
    }

    fun getMessage():Message = message

    abstract fun addEventListener(eventHandler: EventHandler<Event>)

    abstract fun callEventHandler()

}