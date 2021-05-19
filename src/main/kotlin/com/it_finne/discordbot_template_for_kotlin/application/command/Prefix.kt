package com.it_finne.discordbot_template_for_kotlin.application.command

import com.it_finne.discordbot_template_for_kotlin.application.repository.GuildRepository
import com.it_finne.discordbot_template_for_kotlin.error.CommandWarningException
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import mu.KLogger
import mu.KotlinLogging
import net.dv8tion.jda.api.entities.Guild

class Prefix(
    private val logger: KLogger = KotlinLogging.logger {},
    private val guildRepository: GuildRepository = GuildRepository()
): Command() {
    init {
        this.name = "prefix"
    }

    override fun execute(event: CommandEvent) {
        val guild: Guild = event.guild
        val newPrefix: String = event.args.trim()

        if (newPrefix.isEmpty()) {
            throw CommandWarningException("the prefix is not specified.")
        }

        if (newPrefix.contains(Regex("\\p{javaWhitespace}"))) {
            throw CommandWarningException("the prefix cannot contain blanks.")
        }

        guildRepository.update(guild.idLong, prefix=newPrefix).also {
            logger.info { "updated the guild record. guild=${guild.id}, prefix=${newPrefix}" }
        }
        event.replySuccess("the prefix has been updated successfully.")
    }
}
