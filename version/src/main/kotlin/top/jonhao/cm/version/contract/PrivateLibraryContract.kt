package top.jonhao.cm.version.contract

interface PrivateLibraryContract {
    interface CM<T> {
        val base: T
        val publish: T
        val version: T
        val common: T
    }
}