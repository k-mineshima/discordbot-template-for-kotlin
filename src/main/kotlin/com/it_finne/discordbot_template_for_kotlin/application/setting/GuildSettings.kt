package com.it_finne.discordbot_template_for_kotlin.application.setting

import com.it_finne.discordbot_template_for_kotlin.application.entity.GuildEntity
import com.it_finne.discordbot_template_for_kotlin.application.repository.GuildRepository
import com.it_finne.discordbot_template_for_kotlin.error.NotFoundResourceException
import com.it_finne.discordbot_template_for_kotlin.lib.extension.getOrThrow
import com.jagrosh.jdautilities.command.GuildSettingsManager as GuildSettingsManagerInterface
import com.jagrosh.jdautilities.command.GuildSettingsProvider
import net.dv8tion.jda.api.entities.Guild

class GuildSettings(private val guildEntity: GuildEntity): GuildSettingsProvider {
    override fun getPrefixes(): MutableCollection<String> {
        return mutableListOf(this.guildEntity.prefix)
    }
}

class GuildSettingsManager(
    private val guildRepository: GuildRepository = GuildRepository()
): GuildSettingsManagerInterface<GuildSettings> {
    override fun getSettings(guild: Guild): GuildSettings {
        return guildRepository.getByGuildId(guild.idLong)
                              .toEither { NotFoundResourceException("could not find guild record. guild_id=${guild.id}") }
                              .getOrThrow()
                              .let { GuildSettings(it) }
    }
}
