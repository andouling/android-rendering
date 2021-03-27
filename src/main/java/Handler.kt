interface Handler {

    fun post(action: () -> Unit)

    fun sendMessage(message: Message)

    fun handleMessage(message: Message)
}
