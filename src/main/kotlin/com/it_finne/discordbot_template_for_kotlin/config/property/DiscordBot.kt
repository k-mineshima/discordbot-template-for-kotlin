package com.it_finne.discordbot_template_for_kotlin.config.property

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiscordBot(
    val token: String,
    @SerialName("default-prefix")
    val defaultPrefix: String,
    @SerialName("owner-id")
    val ownerId: String,
    @SerialName("emojis")
    val emojis: Emojis
)
