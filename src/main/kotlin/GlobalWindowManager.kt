import view.ViewRootImpl
import javax.swing.JFrame

class GlobalWindowManager {

    private val roots: MutableList<ViewRootImpl> = mutableListOf()
    //val views: MutableList<View> = mutableListOf()
    private val frame: JFrame = JFrame()

    init {
        frame.pack()
        frame.isVisible = true
    }

    fun addRootView(root: ViewRootImpl) {
        roots += root
    }
}