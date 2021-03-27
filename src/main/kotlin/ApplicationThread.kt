class ApplicationThread {
	companion object {

		@JvmStatic
		fun main(args: Array<String>) {
			Looper.prepare()
			val looper: Looper = Looper.myLooper
			looper.loop()


			// ViewRootImpl -> invalidate ->
			// scheduleTraverals -> Choreographer::onNextFrame ->
			// Handler::post -> MessageQueue::add ->
			// Looper::loop (blocking messageQueue::next) -> onMeasure/onLayout/onDraw (traversals)

		}
	}
}



