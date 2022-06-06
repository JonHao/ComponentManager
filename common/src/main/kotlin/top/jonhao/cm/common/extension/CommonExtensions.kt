package top.jonhao.cm.common.extension

/**
 * Non empty judgment.
 *
 * @receiver Any? Any object.
 * @param f Function0<T> Non null callback method.
 * @param t Function0<T> When null callback method.
 * @return T Return you want to get.
 * @since 1.0.0
 */
fun <T> Any?.notNull(f: () -> T, t: () -> T): T {
    return if (this != null) f() else t()
}

/**
 * Not empty execution method.
 *
 * @param value1 T1? Need to determine whether the object is empty.
 * @param value2 T2? Need to determine whether the object is empty.
 * @param bothNotNull Function2<T1, T2, Unit> Both not null to execute method.
 * @since 1.0.0
 */
fun <T1, T2> ifNotNull(value1: T1?, value2: T2?, bothNotNull: (T1, T2) -> (Unit)) {
    value1?.let { v1 ->
        value2?.let { v2 ->
            bothNotNull(v1, v2)
        }
    }
}