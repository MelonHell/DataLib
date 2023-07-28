package ru.melonhell.datalib.config

interface ConfigService {
    fun reload()
    operator fun <T : Any> get(config: Config<T>): T
    operator fun <T : Any> set(config: Config<T>, value: T)
}