class DisplayEventsReceiver(
    private val onNextFrameListener: (frameNanoTime: Long) -> Unit
) {

    fun dispatchFrameTick(frameNanoTime: Long) {
        onNextFrameListener.invoke(frameNanoTime)
    }

}
