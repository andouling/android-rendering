import java.util.concurrent.TimeUnit

/**
 * Метод по типу андроидовского SystemClock.uptimeMillis, ибо [System.nanoTime] отдает не wallclock time, а какое-то
 * свое время
 */
fun uptimeMillis(): Long = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())