package ru.melonhell.datalib.sql.config

import java.io.File

data class DatabaseSettingsJDBC(
    val url: String = "jdbc:mysql://localhost:3306/dbname",
    val driver: String = "com.mysql.jdbc.Driver",
    val username: String? = "root",
    val password: String? = "pa55w0rd"
) : DatabaseSettings {
    override fun url(rootDir: File?) = url
    override fun driver() = driver
    override fun username() = username
    override fun password() = password
}