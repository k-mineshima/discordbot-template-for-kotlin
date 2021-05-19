package com.it_finne.discordbot_template_for_kotlin.application.entity

import java.time.LocalDateTime

data class GuildEntity(
    val id: Long,
    val guildId: Long,
    val name: String,
    val prefix: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
