package ru.melonhell.datalib.jackson.serializer

import com.fasterxml.jackson.core.JsonGenerator

interface JacksonSerializer<T : Any> : JacksonSerializationProcessor<T> {
    fun serialize(value: T, gen: JsonGenerator)
}