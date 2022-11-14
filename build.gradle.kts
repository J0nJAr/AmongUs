import org.apache.tools.ant.filters.ReplaceTokens
//import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    `java-library`
    id("io.papermc.paperweight.userdev") version "1.3.9"
}

group = "bepo.au"
version = "1.0-SNAPSHOT"

repositories{
    maven { url = uri("https://repo.dmulloy2.net/repository/public/") }
}

//repositories {
//    mavenCentral()
//    maven {
//        url 'https://jitpack.io/'
//    }
//    maven {
//        url 'https://papermc.io/repo/repository/maven-public/'
//    }
//    maven {
//        url 'https://repo.dmulloy2.net/nexus/repository/public/'
//    }
//    mavenLocal()
//}

dependencies {
    paperDevBundle("1.19.2-R0.1-SNAPSHOT")
    implementation("com.comphenix.protocol:ProtocolLib:5.0.0-20220727.143709-4")
//    compileOnly 'io.papermc.paper:paper-api:1.19.2-R0.1-SNAPSHOT'
//    compileOnly 'org.spigotmc:spigot:1.19.2-R0.1-SNAPSHOT'
}

tasks {
    // Configure reobfJar to run when invoking the build task
    assemble {
        dependsOn(reobfJar)
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything

        // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
        // See https://openjdk.java.net/jeps/247 for more information.
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
    processResources {
        expand("version" to project.version)
        duplicatesStrategy = org.gradle.api.file.DuplicatesStrategy.INCLUDE
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
    }

    /*
    reobfJar {
      // This is an example of how you might change the output location for reobfJar. It's recommended not to do this
      // for a variety of reasons, however it's asked frequently enough that an example of how to do it is included here.
      outputJar.set(layout.buildDirectory.file("libs/PaperweightTestPlugin-${project.version}.jar"))
    }
     */
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}


//bukkit {
//    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
//    main = "bepo.au.Main"
//    apiVersion = "1.19"
//    authors = listOf("J0nJAr","Kimbepo","Macham")
//
//}