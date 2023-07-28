package ru.melonhell.datalib.playerDataStorage

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import ru.melonhell.datalib.config.YamlBuilderSettings
import java.io.File
import java.util.*
import kotlin.reflect.KClass

abstract class YamlPlayerDataStorage<T : Any>(
    private val mapper: YAMLMapper = defaultMapper(),
    private val dir: File,
    private val type: KClass<T>,
    private val factory: (uuid: UUID) -> T,
) : PlayerDataStorage<T> {
    private val cache = HashMap<UUID, T>()
    override fun get(uuid: UUID): T {
        return cache.computeIfAbsent(uuid) {
            val file = File(dir, "$uuid.yml")
            if (!file.exists()) return@computeIfAbsent factory(uuid)
            return@computeIfAbsent mapper.readValue(file, type.java)
        }
    }

    override fun set(uuid: UUID, data: T) {
        cache[uuid] = data
        dir.mkdirs()
        val file = File(dir, "$uuid.yml")
        mapper.writeValue(file, data)
    }

    companion object {
        private val defaultSettings: YamlBuilderSettings = {
            disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
            disable(YAMLGenerator.Feature.USE_NATIVE_TYPE_ID)
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_VALUES)
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            addModule(kotlinModule())
        }

        fun defaultMapper(): YAMLMapper = YAMLMapper.builder().defaultSettings().build()
    }
}