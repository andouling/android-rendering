import view.ViewRootImpl
import javax.swing.JPanel

//aka ActivityThread
class ApplicationThread {

	companion object {

		@JvmStatic
		fun main(args: Array<String>) {
			Looper.prepare()
			val looper: Looper = Looper.myLooper
			Handler().postDelayed(5000) {
				val windowManager = GlobalWindowManager()
				val windowSpecs = Specs(width = 300, height = 300)
				windowManager.addRootView(ViewRootImpl(windowSpecs, JPanel()))
			}
			looper.loop()

			// ViewRootImpl -> invalidate ->
			// scheduleTraverals -> Choreographer::onNextFrame ->
			// Handler::post -> MessageQueue::add ->
			// Looper::loop (blocking messageQueue::next) -> onMeasure/onLayout/onDraw (traversals)

		}
	}
}



