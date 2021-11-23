package xyz.leendvan.event

import java.util.*

class EventQueue<T> : LinkedList<T>() where T:Event{

    fun getNextEvent():T{
        return pollFirst()
    }
}