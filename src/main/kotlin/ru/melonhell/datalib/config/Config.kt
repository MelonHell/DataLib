package ru.melonhell.datalib.config

import java.io.File
import kotlin.reflect.KClass

data class Config<T : Any>(
    val type: KClass<T>,
    val file: File,
    val factory: () -> T,
) {
    companion object {
        inline fun <reified T: Any> of(file: File, noinline factory: () -> T): Config<T> {
            return Config(T::class, file, factory)
        }
    }
}


