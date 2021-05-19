package com.it_finne.discordbot_template_for_kotlin.application.listener

import com.it_finne.discordbot_template_for_kotlin.application.repository.GuildRepository
import mu.KLogger
import mu.KotlinLogging
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class ReadyListener(
    private val logger: KLogger = KotlinLogging.logger {},
    private val guildRepository: GuildRepository = GuildRepository()
): ListenerAdapter() {
    override fun onReady(event: ReadyEvent) {
        val guilds: List<Guild> = event.jda.guilds

        guildRepository.delete( *(guildRepository.getAll().map { it.guildId } - guilds.map { it.idLong }).toLongArray() )
        guilds.forEach {
            guildRepository.insertOrUpdate(it.idLong, it.name)
        }
        logger.info { "ready to use BOT!" }
    }
}
