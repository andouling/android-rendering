import java.util.concurrent.atomic.AtomicBoolean

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
    private val exitRequested = AtomicBoolean(false)

    fun loop() {
        while (true) {
            val nextMessage: Message = messageQueue.next() ?: return
            nextMessage.target.handleMessage(nextMessage)
        }
    }

    fun shutdownGracefully() {
        println("request shutdown")
        exitRequested.compareAndSet(false, true)
        messageQueue.exit()
    }

}