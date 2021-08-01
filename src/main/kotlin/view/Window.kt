package view

import Specs

class Window {
	
	//todo recalculate on configuration changes
	val specs: Specs = Specs(width = 400, height = 800)
	
	fun addView(view: View) {
		//todo how to do view init
	
		view.measure(specs)
		view.requestLayout()
	}
	
}