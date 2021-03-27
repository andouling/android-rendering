interface Message : Comparable<Message> {
    val target: Handler
    val tag: String
    val action: () -> Unit
    val timeMillis: Long
}