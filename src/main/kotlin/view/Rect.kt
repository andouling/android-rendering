package view

import Specs
import java.awt.Graphics

class Rect : View(Specs(300, 300)) {

    override fun onMeasure(parentSpecs: Specs) {
        //match parent behavior
        measuredSpecs = parentSpecs.copy()
    }

    override fun onDraw(canvas: Graphics) {
        canvas.drawRect(0, 0, measuredSpecs!!.width, measuredSpecs!!.height)
    }
}