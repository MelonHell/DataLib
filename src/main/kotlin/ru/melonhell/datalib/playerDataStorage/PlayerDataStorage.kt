package ru.melonhell.datalib.playerDataStorage

import java.util.*

interface PlayerDataStorage<T : Any> {
    operator fun get(uuid: UUID): T
    operator fun set(uuid: UUID, data: T)
    fun modify(uuid: UUID, function: (it: T) -> T) {
        set(uuid, function(get(uuid)))
    }
}
