package com.it_finne.discordbot_template_for_kotlin.listeners

import com.it_finne.discordbot_template_for_kotlin.conf.Application
import com.it_finne.discordbot_template_for_kotlin.entity.GuildRecord
import com.it_finne.discordbot_template_for_kotlin.entity.GuildRecords
import mu.KLogger
import mu.KotlinLogging
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.events.guild.GuildJoinEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.jetbrains.exposed.sql.transactions.transaction

private val logger: KLogger = KotlinLogging.logger {}

class GuildJoinListener: ListenerAdapter() {
    override fun onGuildJoin(event: GuildJoinEvent) {
        this.onGuildJoin(event.guild)
    }

    fun onGuildJoin(guild: Guild) {
        val guildRecord: GuildRecord? = transaction {
            GuildRecord.find { GuildRecords.guildId eq guild.idLong }.firstOrNull()
        }

        if (guildRecord != null) {
            transaction {
                guildRecord.name = guild.name
            }
            logger.info { "updated the guild record. guild=${guild.id}, name=${guild.name}" }
            return
        }

        transaction {
            GuildRecord.new {
                guildId = guild.idLong
                name = guild.name
                prefix = Application.configuration.discordbot.defaultPrefix
            }
        }
        logger.info { "created new guild record. guild=${guild.id}, name=${guild.name}" }
    }
}
