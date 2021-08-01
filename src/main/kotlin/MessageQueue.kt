import java.util.concurrent.BlockingQueue
import java.util.concurrent.PriorityBlockingQueue

private val quitMessage: Message = object : Message {
    override val target: Handler
        get() = TODO("Not yet implemented")
    override val tag: String
        get() = TODO("Not yet implemented")
    override val action: () -> Unit
        get() = TODO("Not yet implemented")
    override val timeMillis: Long
        get() = TODO("Not yet implemented")

    override fun compareTo(other: Message): Int {
        return timeMillis.compareTo(other.timeMillis)
    }
}

/**
 * todo doc
 */
class MessageQueue {

    /**
     * Используется структуда данных minHeap чтобы обеспечить сортировку по зашедуленному времени экшена
     */
    private val queue: BlockingQueue<Message> = PriorityBlockingQueue(16) { current, other ->
        current.timeMillis.compareTo(other.timeMillis)
    }

    fun add(message: Message) {
        queue.add(message)
    }

    fun next(): Message? {
        var currentMessage: Message?
        println("start ${uptimeMillis()}")
        do {
            currentMessage = queue.peek()
            if (currentMessage == quitMessage) {
                return null
            }
            // ждем пока придет время обработать сообщение. Ждем без делея ибо может появиться новый элемент в любое время.
        } while (currentMessage == null || currentMessage.timeMillis > uptimeMillis())
        //Берем минимальный, даже если успели вставить элемент до того как мы перестали крутиться по циклу
        println("poll ${uptimeMillis()}")
        return queue.poll()
    }

    fun exit() {
        queue.add(quitMessage)
    }
}