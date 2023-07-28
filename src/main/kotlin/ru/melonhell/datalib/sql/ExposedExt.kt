package ru.melonhell.datalib.sql

import org.jetbrains.exposed.sql.Database
import ru.melonhell.datalib.sql.config.DatabaseSettings
import java.io.File

fun Database.Companion.connect(settings: DatabaseSettings, rootDir: File) = connect(
    settings.url(rootDir),
    settings.driver(),
    settings.username() ?: "",
    settings.password() ?: ""
)