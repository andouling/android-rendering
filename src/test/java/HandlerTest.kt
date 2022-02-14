import org.junit.Test
import java.util.*

class HandlerTest {

    val looper = Looper()
    val handler = DefaultHandler(looper)

    @Test
    fun tekitounaTest() {

        val currentTime = uptimeMillis()
        handler.sendMessage(DefaultMessage(handler, "task", { println(uptimeMillis()) }, currentTime + 15000))
        handler.sendMessage(DefaultMessage(handler, "task", { println(uptimeMillis()) }, currentTime + 10000))
        handler.sendMessage(DefaultMessage(handler, "task", { println(uptimeMillis()) }, currentTime + 5000))

        /*repeat(3) {
            val message: Message = looper.messageQueue.next()
            assertTrue(uptimeMillis() - message.timeMillis < 10)
        }*/
        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.sendMessage(DefaultMessage(handler, "task", { println("task ${uptimeMillis()}")}))
                Thread.sleep(5000)
                looper.shutdownGracefully()
            }
        }, 20000)
        looper.loop()
    }
}