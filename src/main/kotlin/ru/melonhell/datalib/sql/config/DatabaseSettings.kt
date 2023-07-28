package ru.melonhell.datalib.sql.config

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.io.File

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
    Type(value = DatabaseSettingsH2::class, name = "H2"),
    Type(value = DatabaseSettingsJDBC::class, name = "JDBC"),
    Type(value = DatabaseSettingsMariaDB::class, name = "MariaDB"),
    Type(value = DatabaseSettingsMySQL::class, name = "MySQL"),
    Type(value = DatabaseSettingsPostgres::class, name = "Postgres"),
    Type(value = DatabaseSettingsSQLite::class, name = "SQLite"),
)
interface DatabaseSettings {
    fun url(rootDir: File? = null): String
    fun driver(): String
    fun username(): String? = null
    fun password(): String? = null
}