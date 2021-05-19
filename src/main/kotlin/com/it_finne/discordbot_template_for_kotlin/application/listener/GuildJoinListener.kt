package com.it_finne.discordbot_template_for_kotlin.application.listener

import arrow.core.Either
import com.it_finne.discordbot_template_for_kotlin.application.repository.GuildRepository
import mu.KLogger
import mu.KotlinLogging
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class GuildJoinListener(
    private val logger: KLogger = KotlinLogging.logger {},
    private val guildRepository: GuildRepository = GuildRepository()
): ListenerAdapter() {
    override fun onGuildJoin(event: GuildJoinEvent) {
        val guild: Guild = event.guild
        guildRepository.insert(guild.idLong, guild.name).also {
            when (it) {
                is Either.Right -> logger.info { "created guild record. guild=${guild.id}, name=${guild.name}" }
                is Either.Left -> logger.error(it.value) { it.value.message }
            }
        }
    }
}
