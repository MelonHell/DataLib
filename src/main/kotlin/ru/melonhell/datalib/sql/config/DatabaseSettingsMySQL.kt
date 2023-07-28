package ru.melonhell.datalib.sql.config

import java.io.File

data class DatabaseSettingsMySQL(
    var host: String = "localhost",
    var port: Int = 3306,
    var database: String = "dbname",
    var username: String = "root",
    var password: String = "pa55w0rd"
) : DatabaseSettings {
    override fun url(rootDir: File?): String = "jdbc:mysql://${host}:${port}/${database}"
    override fun driver(): String = "com.mysql.jdbc.Driver"
    override fun username() = username
    override fun password() = password
}