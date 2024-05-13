plugins {
    `java-library`
    `maven-publish`
    id("io.freefair.lombok") version "8.0.1"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
    maven { url = uri("https://repo.maven.apache.org/maven2/") }
    maven { url = uri("https://repo.codemc.io/repository/maven-public/") }
    maven { url = uri("https://maven.enginehub.org/repo/") }
    maven { url = uri("https://hub.jeff-media.com/nexus/repository/jeff-media-public/") }
    maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

dependencies {
    compileOnly("org.spigotmc:spigot:1.17-R0.1-SNAPSHOT")
    implementation("net.kyori:adventure-platform-bukkit:4.3.2")
    implementation("net.kyori:adventure-text-serializer-plain:4.17.0")
    implementation("net.kyori:adventure-text-minimessage:4.17.0")
    implementation("org.jetbrains:annotations:24.0.1")
    implementation("dev.jorel:commandapi-bukkit-shade:9.3.0")
    implementation("com.github.stefvanschie.inventoryframework:IF:0.10.13")
    implementation("com.jeff_media:MorePersistentDataTypes:2.4.0")
    implementation("com.jeff-media:custom-block-data:2.2.2")
    implementation(platform("com.intellectualsites.bom:bom-newest:1.43"))
    compileOnly("com.fastasyncworldedit:FastAsyncWorldEdit-Core")
    compileOnly("com.fastasyncworldedit:FastAsyncWorldEdit-Bukkit") { isTransitive = false }
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.8-SNAPSHOT")
    compileOnly("de.tr7zw:item-nbt-api-plugin:2.12.3")
}

group = "me.wega"
version = "0.1.0b"
description = "Common usage util for plugins made by Wega"
java.sourceCompatibility = JavaVersion.VERSION_17

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group.toString()
            artifactId = rootProject.name
            version = version
            from(components["java"])
        }
    }
}

tasks {
    build {
        dependsOn("shadowJar")
    }

    shadowJar {
        archiveFileName.set("${project.name}-${project.version}.jar")
        relocate("com.jeff_media.morepersistentdatatypes", "me.wega.shadow.morepersistentdatatypes")
        relocate("dev.jorel.commandapi", "me.wega.shadow.commandapi")
        relocate("com.github.stefvanschie.inventoryframework", "me.wega.shadow.IF")
        relocate("com.jeff_media.customblockdata", "me.wega.shadow.customblockdata")
        relocate("net.kyori", "me.wega.shadow.kyori")
    }


    compileJava {
        options.encoding = Charsets.UTF_8.name()

        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
    processResources {
        filesMatching("plugin.yml") {
            expand("version" to project.version)
        }
    }
}
