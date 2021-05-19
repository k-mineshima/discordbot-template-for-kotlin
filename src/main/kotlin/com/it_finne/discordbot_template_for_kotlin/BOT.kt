package com.it_finne.discordbot_template_for_kotlin

import com.it_finne.discordbot_template_for_kotlin.application.command.Prefix
import com.it_finne.discordbot_template_for_kotlin.config.Application
import com.it_finne.discordbot_template_for_kotlin.application.listener.CommandExceptionListener
import com.it_finne.discordbot_template_for_kotlin.application.listener.GuildJoinListener
import com.it_finne.discordbot_template_for_kotlin.application.listener.ReadyListener
import com.it_finne.discordbot_template_for_kotlin.application.setting.GuildSettingsManager
import com.jagrosh.jdautilities.command.CommandClient
import com.jagrosh.jdautilities.command.CommandClientBuilder
import mu.KLogger
import mu.KotlinLogging
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity

private val logger: KLogger = KotlinLogging.logger {}

class BOT {
    private val discordbot: JDA

    init {
        logger.info { "the application has started in '${Application.applicationMode}' mode." }

        val commandClient: CommandClient = CommandClientBuilder()
            .setStatus(OnlineStatus.ONLINE)
            .setActivity(Activity.playing("!help"))
            .setOwnerId(Application.configuration.discordbot.ownerId)
            .addCommands(
                Prefix()
            )
            .setGuildSettingsManager(GuildSettingsManager())
            .setEmojis(
                Application.configuration.discordbot.emojis.success,
                Application.configuration.discordbot.emojis.warning,
                Application.configuration.discordbot.emojis.error
            )
            .setListener(CommandExceptionListener())
            .build()

        this.discordbot = JDABuilder
            .createDefault(Application.configuration.discordbot.token)
            .addEventListeners(
                commandClient,
                GuildJoinListener(),
                ReadyListener()
            )
            .build()
    }
}
