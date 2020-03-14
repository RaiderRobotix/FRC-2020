package com.raiderrobotix._2020.util

import edu.wpi.first.networktables.NetworkTable
import edu.wpi.first.networktables.NetworkTableEntry
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import org.ghrobotics.lib.wrappers.networktables.NetworkTableEntryDelegate
import org.ghrobotics.lib.wrappers.networktables.delegate
import org.ghrobotics.lib.wrappers.networktables.get

@Suppress("unchecked")
inline operator fun <reified T> NetworkTableEntry.invoke(default: T): NetworkTableEntryDelegate<T> = when (T::class) {
    String::class -> delegate(defaultValue = default as String)
    Double::class -> delegate(defaultValue = default as Double)
    Boolean::class -> delegate(defaultValue = default as Boolean)
    else -> throw IllegalArgumentException("Illegal Generic")
} as NetworkTableEntryDelegate<T>

operator fun <T> NetworkTable.invoke(default: T) = NetworkTableDelegate<T>(this, default = default)

class NetworkTableDelegate<T>(private val table: NetworkTable, private val default: T) : ReadWriteProperty<Any, T> {

    override operator fun getValue(thisRef: Any, property: KProperty<*>): T =
        table[property.name].value.value as? T
            ?: default

    override operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        table[property.name].setValue(value)
    }
}
