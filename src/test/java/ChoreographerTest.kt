import org.junit.jupiter.api.Test

class ChoreographerTest {

    val looper = Looper()
    val choreographer = Choreographer(looper)
    var frameNumber = 1

    @Test
    fun tekitounaTesuto() {
        choreographer.postOnNextFrame {
            println("FrameNumber ${frameNumber++}")
            postYetAnotherFrame()
        }
        looper.loop()
    }

    private fun postYetAnotherFrame() {
        if (frameNumber == 10) {
            looper.shutdownGracefully()
            return
        }
        choreographer.postOnNextFrame {
            println("FrameNumber ${frameNumber++}")
            postYetAnotherFrame()
        }
    }
}