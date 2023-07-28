package ru.melonhell.datalib.sql.config

import java.io.File

data class DatabaseSettingsH2(
    var file: String = "database"
) : DatabaseSettings {
    override fun url(rootDir: File?): String = "jdbc:h2:file:${(rootDir?.resolve(file) ?: File(file)).absolutePath}"
    override fun driver(): String = "org.h2.Driver"
}