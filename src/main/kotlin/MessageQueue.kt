import java.util.*
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock

/**
 * todo doc
 * Класс с наивной реализацией тредсейфности.
 */
class MessageQueue {

    private val lock = ReentrantLock()
    private val isEmpty: Condition = lock.newCondition()

    //todo replace with my fucking awesome queue implementation with O(1) for all operations
    private val queue: LinkedList<Message> = LinkedList()

    fun add(message: Message) {
        lock.lock()
        try {
            queue.add(message)
            //todo неоптимальная сортировочка на каждую вставку. TODO: запилить свою реализацию очереди
            queue.sort()
            isEmpty.signalAll()
        } finally {
            lock.unlock()
        }
    }

    fun next(): Message? {
        if (queue.isEmpty()) {
            //todo re-acquire
            isEmpty.await()
        }

        //ждем пока придет время обработать сообщение. Ждем без делея ибо может появиться новый элемент в любое время.
        while (queue.peek().timeMillis <= System.currentTimeMillis());
        return queue.poll()
    }
}