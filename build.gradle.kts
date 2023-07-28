plugins {
    alias(libs.plugins.kotlin.jvm)
    `maven-publish`
}

group = "ru.melonhell.datalib"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api(libs.bundles.jackson)
    api(libs.bundles.exposed)

    api(libs.mongojack)

    api(libs.kyori.adventure.api)
    api(libs.kyori.adventure.text.minimessage)
}

java.withSourcesJar()
publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = rootProject.name
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
        maven {
            url = uri("https://maven.melonhell.ru/public/")
            credentials {
                username = findProperty("MELONHELL_REPO_USR")?.toString()
                password = findProperty("MELONHELL_REPO_PSW")?.toString()
            }
        }
    }
}