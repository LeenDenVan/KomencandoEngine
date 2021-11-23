package xyz.leendvan.event

class EventBus {

    private lateinit var eventQueue:EventQueue<Event>

    fun init(){

    }

    fun subscribeEvent(event: Event){

    }

    fun sendEvent(event: Event){
        eventQueue.add(event)
    }

    fun handleEvents(){
        eventQueue.forEach{
            it.callEventHandler()
        }
    }
}