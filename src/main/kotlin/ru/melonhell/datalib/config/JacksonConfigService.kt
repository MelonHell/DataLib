package ru.melonhell.datalib.config

import com.fasterxml.jackson.databind.ObjectMapper


class JacksonConfigService(
    private val mapper: ObjectMapper,
    private val configs: List<Config<*>>,
) : ConfigService {
    private val cache = hashMapOf<Config<*>, Any>()

    init {
        reload()
    }

    override fun reload() = configs.forEach { reload(it) }

    @Suppress("UNCHECKED_CAST")
    override operator fun <T : Any> get(config: Config<T>): T = cache[config] as T

    override operator fun <T : Any> set(config: Config<T>, value: T) {
        cache[config] = value
        write(config, value)
    }

    private fun <T : Any> reload(config: Config<T>) {
        val value = read(config)
        write(config, value)
        cache[config] = value
    }

    private fun <T : Any> read(config: Config<T>): T {
        return if (config.file.exists()) mapper.readValue(config.file, config.type.java)
        else config.factory()
    }

    private fun <T : Any> write(config: Config<T>, value: T) {
        mapper.writeValue(config.file, value)
    }
}