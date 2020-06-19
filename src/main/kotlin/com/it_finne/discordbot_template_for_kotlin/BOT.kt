package com.it_finne.discordbot_template_for_kotlin

import com.it_finne.discordbot_template_for_kotlin.conf.Application
import com.jagrosh.jdautilities.command.CommandClient
import com.jagrosh.jdautilities.command.CommandClientBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity

class BOT {
    private val discordbot: JDA

    init {
        val commandClient: CommandClient = CommandClientBuilder()
            .setPrefix(Application.configuration.discordbot.defaultPrefix)
            .setStatus(OnlineStatus.ONLINE)
            .setActivity(Activity.playing("!help"))
            .setOwnerId(Application.configuration.discordbot.ownerId)
            .addCommands(*Application.commands)
            .build()

        this.discordbot = JDABuilder
            .createDefault(Application.configuration.discordbot.token)
            .addEventListeners(commandClient)
            .build()
    }
}
