package top.jonhao.cm.version.entity

import top.jonhao.cm.base.extension.times

open class DependencyEntity protected constructor(
    open val group: String,
    open val name: String,
    open var version: String
) {
    companion object {
        fun get(group: String, name: String, version: String) =
            DependencyEntity(
                group,
                name,
                version
            )

        fun getNotation(group: String, name: String, version: String) =
            get(group, name, version).toString()
    }

    infix fun version(version: String): String {
        this.version = version
        return toString()
    }

    override fun toString(): String {
        return group * name * version
    }
}

