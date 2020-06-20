package com.it_finne.discordbot_template_for_kotlin.listeners

import mu.KLogger
import mu.KotlinLogging
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

private val logger: KLogger = KotlinLogging.logger {}

class ReadyListener: ListenerAdapter() {
    override fun onReady(event: ReadyEvent) {
        val guilds: List<Guild> = event.jda.guilds

        val guildJoinListener: GuildJoinListener = GuildJoinListener()
        guilds.forEach {
            guildJoinListener.onGuildJoin(it)
        }
        logger.info { "ready to use BOT!" }
    }
}
