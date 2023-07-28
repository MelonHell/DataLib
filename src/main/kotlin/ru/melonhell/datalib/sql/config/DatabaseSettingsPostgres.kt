package ru.melonhell.datalib.sql.config

import java.io.File

data class DatabaseSettingsPostgres(
    var host: String = "localhost",
    var port: Int = 5432,
    var database: String = "dbname",
    var username: String = "root",
    var password: String = "pa55w0rd"
) : DatabaseSettings {
    override fun url(rootDir: File?): String = "jdbc:postgresql://${host}:${port}/${database}"
    override fun driver(): String = "org.postgresql.Driver"
    override fun username() = username
    override fun password() = password
}