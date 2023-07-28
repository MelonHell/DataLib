package ru.melonhell.datalib.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import ru.melonhell.datalib.jackson.CommentsYamlMapper

typealias YamlBuilderSettings = YAMLMapper.Builder.() -> YAMLMapper.Builder

fun yamlConfigServiceOf(
    configs: List<Config<*>>,
    builderSettings: YamlBuilderSettings = defaultSettings,
): JacksonConfigService {
    return JacksonConfigService(CommentsYamlMapper(YAMLMapper.builder().builderSettings().build()), configs)
}

val defaultSettings: YamlBuilderSettings = {
    configure(YAMLGenerator.Feature.WRITE_DOC_START_MARKER, false)
    configure(YAMLGenerator.Feature.USE_NATIVE_TYPE_ID, false)
    configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_VALUES, true)
    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    addModule(kotlinModule())
}
