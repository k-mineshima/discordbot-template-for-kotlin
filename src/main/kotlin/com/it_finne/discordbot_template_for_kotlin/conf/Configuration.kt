package com.it_finne.discordbot_template_for_kotlin.conf

import com.charleskorn.kaml.Yaml
import com.it_finne.discordbot_template_for_kotlin.ApplicationMode
import kotlinx.serialization.Serializable

@Serializable
data class Configuration(
    val discordbot: DiscordBot,
    val database: Database
) {
    companion object {
        fun load(applicationMode: ApplicationMode): Configuration {
            val filename = "/application-${applicationMode.value}.yaml"
            return Yaml.default.parse(
                serializer(),
                Configuration::class.java.getResource(filename).readText()
            )
        }
    }
}
