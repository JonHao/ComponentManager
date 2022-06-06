package top.jonhao.cm.base.tool

object ClassUtils {
    fun isClassExist(className: String): Boolean {
        try {
            Class.forName(className)
        } catch (e: ClassNotFoundException) {
            return false
        }
        return true
    }
}