class Looper {

    companion object {
        private val threadAssociatedLooper = ThreadLocal<Looper>()
        var myLooper: Looper
            get() = threadAssociatedLooper.get() ?: throw IllegalStateException("Asshole, u didn't prepare() looper with caller thread")
            private set(value) = threadAssociatedLooper.set(value)

        fun prepare() {
            //todo set current thread to associate thread with looper
            if (threadAssociatedLooper.get() != null) {
                throw IllegalStateException("U man cannot associate new looper with already associated thread.")
            }
            myLooper = Looper()
        }
    }

    val messageQueue = MessageQueue()

    fun loop() {
        while (true) {
            val nextMessage: Message = messageQueue.next() ?: return
            nextMessage.target.handleMessage(nextMessage)
        }
    }

}