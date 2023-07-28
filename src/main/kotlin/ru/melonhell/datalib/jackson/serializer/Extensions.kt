package ru.melonhell.datalib.jackson.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import kotlin.reflect.KClass


@Suppress("UNCHECKED_CAST")
val <T : Any> JacksonSerializationProcessor<T>.type get() = this::class.supertypes.first().arguments.first().type!!.classifier as KClass<T>

fun <T : Any> SimpleModule.addSerializationProcessor(sp: JacksonSerializationProcessor<T>) {
    if (sp is JacksonSerializer) {
        addSerializer(sp.type.java, object : StdSerializer<T>(sp.type.java) {
            override fun serialize(value: T, gen: JsonGenerator, provider: SerializerProvider?) =
                sp.serialize(value, gen)
        })
    }

    if (sp is JacksonDeserializer) {
        addDeserializer(sp.type.java, object : StdDeserializer<T>(sp.type.java) {
            override fun deserialize(jsonParser: JsonParser, ctxt: DeserializationContext?) =
                sp.deserialize(jsonParser)
        })
    }
}

fun Iterable<JacksonSerializationProcessor<*>>.module() =
    SimpleModule().apply { this@module.forEach { addSerializationProcessor(it) } }