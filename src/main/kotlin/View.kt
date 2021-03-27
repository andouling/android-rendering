import javax.swing.JPanel

interface View {

	//todo refactor to ViewGroup in future
	val parent: Window

	val specs: Specs

	val choreographer: Choreographer

	/**
	 * todo doc
	 * return computated bounds of the current View in [Specs] format
	 */
	fun onMeasure(parentSpecs: Specs): Specs

	/**
	 * todo doc
	 */
	fun onLayout(measuredSpecs: Specs)

	/**
	 * todo doc
	 */
	fun onDraw(measuredSpecs: Specs, canvas: JPanel)

	fun requestMeasure()

	fun requestLayout()

}

abstract class BaseView(
	override val parent: Window,
	override val specs: Specs,
	override val choreographer: Choreographer
) : View {

	override fun requestMeasure() {
		onMeasure(parent.specs)
	}

	override fun requestLayout() {
		onLayout(onMeasure(parent.specs))
	}

	fun invalidate() {

	}

	private fun scheduleTraversals() {
		choreographer.postOnNextFrame {
			doTraversals()
		}
	}

	private fun doTraversals() {
		//measure
		//layout
		//draw
	}

}