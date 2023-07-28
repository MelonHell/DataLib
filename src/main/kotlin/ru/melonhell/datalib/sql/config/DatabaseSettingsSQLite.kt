package ru.melonhell.datalib.sql.config

import java.io.File

data class DatabaseSettingsSQLite(
    var file: String = "database.sqlite"
) : DatabaseSettings {
    override fun url(rootDir: File?): String = "jdbc:sqlite:${(rootDir?.resolve(file) ?: File(file)).absolutePath}"
    override fun driver(): String = "org.sqlite.JDBC"
}