package com.it_finne.discordbot_template_for_kotlin.conf

import kotlinx.serialization.Serializable

@Serializable
data class Emojis(
    val success: String,
    val warning: String,
    val error: String
)
