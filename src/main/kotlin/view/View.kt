package view

import Specs
import java.awt.Graphics
import java.lang.Integer.min

open class View(
	//todo pass context
	val bounds: Specs = Specs(0, 0)
) {

	//todo refactor to ViewGroup in future
	//todo Add GlobalWindowManager and WindowManager
	var parent: ViewParent? = null

	var measuredSpecs: Specs? = null

	/**
	 * todo doc
	 * Computes bounds of the current view.View in [Specs] format
	 */
	open fun onMeasure(parentSpecs: Specs) {
		val height: Int = min(parentSpecs.height, bounds.height)
		val width: Int = min(parentSpecs.width, bounds.width)
		measuredSpecs = Specs(width = width, height = height)
	}

	/**
	 * todo doc
	 */
	open fun onLayout(layoutSpecs: LayoutSpecs) {
		//since view isn't ViewGroup – skip the layouting
	}

	/**
	 * todo doc
	 */
	open fun onDraw(canvas: Graphics) {
		//write logic in child View
	}

	fun requestLayout() {
		parent?.requestLayout()
	}

	fun measure(parentSpecs: Specs) {
		onMeasure(parentSpecs)
	}

	fun layout(layoutSpecs: LayoutSpecs) {
		onLayout(layoutSpecs)
	}

	/**
	 * 1) drawBackground (redundant for us right now)
	 * 2) drawContent
	 */
	fun draw(canvas: Graphics) {
		onDraw(canvas)
	}

	fun invalidate() {
		parent?.invalidateChild()
	}

	internal fun assignParent(viewParent: ViewParent?) {
		parent = viewParent
	}

}

data class LayoutSpecs(val left: Int, val top:Int, val right: Int, val bottom: Int)

interface ViewParent {
	fun requestLayout()
	fun invalidateChild()
}