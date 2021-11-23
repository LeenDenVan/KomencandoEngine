package xyz.leendvan.event

class Message {

    private var tag:String = ""
    private var messageHeader:HashMap<String, String> = HashMap()
    private var messageBody:String = TODO()

    class MessageBuilder{
        private var message:Message = Message()

        fun setTag(tag:String){
            message.tag = tag
        }

        fun setMessageHeader(messageHeader:HashMap<String, String>){
            message.messageHeader = messageHeader
        }

        fun addMessageHeader(tag:String, header:String){
            message.messageHeader.put(tag, header)
        }

        fun setMessageBody(body:String){
            message.messageBody = body
        }

        fun getResult():Message = message
    }

    fun getBuilder():MessageBuilder = MessageBuilder()

    fun getTag() = tag

    fun getHeader() = messageHeader

    fun getHeaderByTag(htag:String) = messageHeader[htag]

    fun getBody() = messageBody


}