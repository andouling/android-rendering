import java.util.concurrent.BlockingQueue
import java.util.concurrent.PriorityBlockingQueue

object QuitMessage : DefaultMessage(Handler(Looper.myLooper), "quit", {}, 0)

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
            //todo тута вот засинхронайзить
            currentMessage = queue.peek()
            if (currentMessage is QuitMessage) {
                return null
            }
            // ждем пока придет время обработать сообщение. Ждем без делея ибо может появиться новый элемент в любое время.
        } while (currentMessage == null || currentMessage.timeMillis > uptimeMillis())
        //Берем минимальный, даже если успели вставить элемент до того как мы перестали крутиться по циклу
        println("poll ${uptimeMillis()}")
        return queue.poll()
    }

    fun exit() {
        queue.add(QuitMessage)
    }
}