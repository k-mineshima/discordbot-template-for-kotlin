package com.it_finne.discordbot_template_for_kotlin

import com.it_finne.discordbot_template_for_kotlin.entity.GuildRecord
import com.it_finne.discordbot_template_for_kotlin.entity.GuildRecords
import com.jagrosh.jdautilities.command.GuildSettingsManager as GuildSettingsManagerInterface
import com.jagrosh.jdautilities.command.GuildSettingsProvider
import mu.KLogger
import mu.KotlinLogging
import net.dv8tion.jda.api.entities.Guild
import org.jetbrains.exposed.sql.transactions.transaction

private val logger: KLogger = KotlinLogging.logger {}

class GuildSettings(private val guildRecord: GuildRecord): GuildSettingsProvider {
    override fun getPrefixes(): MutableCollection<String> {
        return mutableListOf(this.guildRecord.prefix)
    }
}

class GuildSettingsManager: GuildSettingsManagerInterface<GuildSettings> {
    override fun getSettings(guild: Guild): GuildSettings? {
        val guildRecord: GuildRecord = transaction {
            GuildRecord.find { GuildRecords.guildId eq guild.idLong }.firstOrNull()
        } ?: run {
            logger.warn { "could not find guild record. guild_id: ${guild.id}" }
            return null
        }

        return GuildSettings(guildRecord)
    }
}
