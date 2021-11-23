package xyz.leendvan.event

interface EventHandler<in Event> {
    fun handle(event:Event)
}