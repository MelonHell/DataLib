package ru.melonhell.datalib.jackson.serializer.impl

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import ru.melonhell.datalib.jackson.serializer.JacksonDeserializer
import ru.melonhell.datalib.jackson.serializer.JacksonSerializer

class MiniMessageSerializationProcessor(
    private val miniMessage: MiniMessage = MiniMessage.miniMessage(),
) : JacksonSerializer<Component>, JacksonDeserializer<Component> {
    override fun deserialize(parser: JsonParser): Component {
        return miniMessage.deserialize(parser.text)
    }

    override fun serialize(value: Component, gen: JsonGenerator) {
        gen.writeString(miniMessage.serialize(value))
    }
}