import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec

val PluginDependenciesSpec.jvm get() = kotlin("jvm").version(Versions.Plugin.KOTLIN)
val PluginDependenciesSpec.kotlinSerialization get() = kotlin("plugin.serialization").version(Versions.Plugin.KOTLIN)
val PluginDependenciesSpec.flyway get() = id("org.flywaydb.flyway").version(Versions.Plugin.FLYWAY)
