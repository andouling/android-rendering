package view

import Choreographer
import Specs

class ViewRootImpl(
    val windowSpecs: Specs
) : ViewParent {

    private val choreographer: Choreographer = Choreographer.getInstance()
    var view: View? = null
        set(rootView) {
            if (field != null) {
                return
            }
            field = rootView
        }

    override fun requestLayout() {
        TODO("Not yet implemented")
    }

    override fun invalidateChild() {
        invalidate()
    }

    private fun invalidate() {
        scheduleTraversals()
    }

    private fun scheduleTraversals() {
        choreographer.postOnNextFrame {
            doTraversals()
        }
    }

    private fun doTraversals() {
        val hostView: View = view ?: return
        view?.measure(windowSpecs)
        view?.layout(LayoutSpecs(0, 0, hostView.measuredSpecs!!.width, hostView.measuredSpecs!!.height))
        //todo threadedRenderer?? view?.draw()
    }

}