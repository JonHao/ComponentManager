package top.jonhao.cm.base.extension

operator fun String.times(other: String): String {
    if (this.isEmpty()) {
        return other
    }
    if (other.isEmpty()) {
        return this
    }
    return "$this:$other"
}

fun String.escape(): String = "\"" + this + "\""

infix fun String.version(version: String): String {
    val array = this.split(":")
    assert(array.size == 3)
    return array[0] * array[1] * version
}