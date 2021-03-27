class NativeRendererSimulator {

    fun tick() {
        var lastTime: Long = 0
        while (true) {
            //todo Вызывать хореографер на каждый фрейм и ViewRootImpl#onFrameDraw https://developer.android.com/games/develop/gameloops#choreographer　

            val now: Long = System.nanoTime()
            Thread.sleep(16 - now - lastTime)
            lastTime = now
        }
    }
}